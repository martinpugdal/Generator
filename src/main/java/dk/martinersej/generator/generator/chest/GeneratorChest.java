package dk.martinersej.generator.generator.chest;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.GeneratorItem;
import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.hook.VaultHook;
import dk.martinersej.generator.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class GeneratorChest extends GeneratorElement {
    private final WeakHashMap<GeneratorType, Integer> drops = new WeakHashMap<>();
    private final Gui gui = new Gui(5, "§aGenerator Chest");

    public GeneratorChest(Location location, UUID owner) {
        super(owner, location);
        Generator.getUserManager().getUser(owner).setGeneratorChest(this);
        gui.disableAllInteractions();
        GuiItem filler = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
        gui.getFiller().fillBottom(filler);
        gui.getFiller().fillTop(filler);
        gui.setDefaultClickAction(event -> event.setCancelled(true));
    }

    @Override
    public GeneratorItem createItem() {
        return new GeneratorChestItem();
    }

    public void addDrop(GeneratorType generatorType) {
        drops.putIfAbsent(generatorType, 0);
        drops.computeIfPresent(generatorType, (k, v) -> v + generatorType.getDrop().getAmount());
    }

    public void addDrop(GeneratorType generatorType, long amount) {
        drops.putIfAbsent(generatorType, 0);
        drops.computeIfPresent(generatorType, (k, v) -> Math.toIntExact(v + amount));
    }

    public void removeDrop(GeneratorType generatorType) {
        drops.remove(generatorType);
    }

    public void openGUI(Player player) {
        gui.open(player);
    }

    public void updateGui() {
        for (GeneratorType generatorType : GeneratorType.values()) {
            gui.removeItem(generatorType.ordinal() + 9);
            if (!drops.containsKey(generatorType)) {
                continue;
            }
            GuiItem guiItem = new GuiItem(generatorType.getDrop());
            ItemMeta itemMeta = guiItem.getItemStack().getItemMeta().clone();
            itemMeta.setLore(Arrays.asList("", "§7Klik for at sælge alle "+itemMeta.getDisplayName(), ""));
            itemMeta.setDisplayName(itemMeta.getDisplayName() + " §7x§a" + drops.get(generatorType));
            guiItem.getItemStack().setItemMeta(itemMeta);
            guiItem.getItemStack().setAmount(1);
            GeneratorType generatorTypeCopy = generatorType;
            guiItem.setAction(event -> {
                long generetorAmount = drops.get(generatorType);
                if (generetorAmount > 0) {
                    sell(event.getWhoClicked().getUniqueId(), generatorTypeCopy, generetorAmount);
                    gui.updateItem(event.getSlot(), new ItemStack(Material.AIR));
                    guiItem.setAction(null);
                }
            });
            gui.addItem(guiItem);
        }
        gui.update();
    }

    private long sell(GeneratorType generatorType, long amount) {
        GeneratorType generatorTypeCopy = generatorType;
        removeDrop(generatorType);
        double price = GeneratorType.DropPrice.valueOf(generatorTypeCopy.name()).getPrice();
        return (long) (price * amount);
    }

    private void sell(UUID uuid, GeneratorType generatorType, long amount) {
        long total = sell(generatorType, amount);
        long multiplier = Generator.getUserManager().getUser(uuid).getMultiplier();
        total = total * multiplier;
        Player playerSold = Bukkit.getPlayer(uuid);
        VaultHook.addBalance(playerSold, total);
        Generator.getUserManager().getUser(uuid).addXp(GeneratorType.DropPrice.valueOf(generatorType.name()).getXp() * amount);
        Player ownerOfChest = Bukkit.getPlayer(getOwner());
        if (ownerOfChest != playerSold) {
            ownerOfChest.sendMessage("§e" + playerSold.getName() + " §asolgte for §e" + total + " §amed en multiplier på §e" + multiplier + " §a!");
        }
        playerSold.sendMessage("§aDu solgte for §e" + total + " §amed en multiplier på §e" + multiplier + " §a!");

    }

    public void sellAll(UUID uuid) {
        long sellTotal = 0;
        long xpTotal = 0;
        long multiplier = Generator.getUserManager().getUser(uuid).getMultiplier();
        Set<Map.Entry<GeneratorType, Integer>> drops = new HashSet<>(this.drops.entrySet());
        for (Map.Entry<GeneratorType, Integer> drop : drops) {
            sellTotal += sell(drop.getKey(), drop.getValue());
            xpTotal += GeneratorType.DropPrice.valueOf(drop.getKey().name()).getXp() * drop.getValue();
        }

        Player playerSold = Bukkit.getPlayer(uuid);
        Player ownerOfChest = Bukkit.getPlayer(getOwner());
        VaultHook.addBalance(playerSold, sellTotal);
        Generator.getUserManager().getUser(uuid).addXp(xpTotal);
        if (ownerOfChest != playerSold && ownerOfChest != null) {
            ownerOfChest.sendMessage("§e" + playerSold.getName() + " §asolgte alle drops fra din generator chest!");
        }
        playerSold.sendMessage("§aDu solgte alle drops for §e" + sellTotal + " §amed en multiplier på §e" + multiplier + " §a!");
        updateGui();
    }

    public WeakHashMap<GeneratorType, Integer> getDrops() {
        return drops;
    }

    public Gui getGUI() {
        return gui;
    }
}
