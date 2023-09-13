package dk.martinersej.generator.generator.chest;

import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.GeneratorItem;
import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.hooks.VaultHook;
import dk.martinersej.generator.utils.GUI;
import dk.martinersej.generator.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GeneratorChest extends GeneratorElement {
    private final WeakHashMap<GeneratorType, Integer> drops = new WeakHashMap<>();
    private final PaginatedGui gui;
    private final GeneratorChestGUI sellGUI;

    public GeneratorChest(Location location, UUID owner) {
        super(owner, location);
        Generator.getUserManager().getUser(owner).setGeneratorChest(this);
        gui = new PaginatedGui(5, "§aGenerator Chest" + " §7- §e" + Bukkit.getOfflinePlayer(owner).getName());
        sellGUI = new GeneratorChestGUI("§aGenerator Chest" + " §7- §e" + Bukkit.getOfflinePlayer(owner).getName(), 5, drops);
        gui.disableAllInteractions();
        GuiItem filler = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
        gui.getFiller().fillTop(filler);
        sellGUI.setRow(new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack(), 0);
        sellGUI.setRow(new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack(), 4);
        gui.getFiller().fillBottom(filler);
        gui.setDefaultClickAction(event -> event.setCancelled(true));

        GuiItem backItem = new GuiItem(new ItemBuilder(Material.ARROW).setName("§aForrige side").toItemStack());
        GuiItem nextPageItem = new GuiItem(new ItemBuilder(Material.ARROW).setName("§aNæste side").toItemStack());
        backItem.setAction(event -> {
            gui.previous();
            if (gui.getPagesNum() > 1) {
                gui.setItem(5, 3, backItem);
            } else {
                gui.setItem(5, 3, filler);
            }
        });
        nextPageItem.setAction(event -> {
            gui.next();
            if (gui.getPagesNum() == gui.getCurrentPageNum()) {
                gui.setItem(5, 7, filler);
            } else {
                gui.setItem(5, 7, filler);
            }
        });
        gui.setItem(5, 3, backItem);
        gui.setItem(5, 7, nextPageItem);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
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
//        gui.open(player);
        sellGUI.open(player);
    }

    public void updateGui() {
        gui.clearPageItems();
        sellGUI.clearItems();
        for (GeneratorType generatorType : GeneratorType.values()) {
            if (!drops.containsKey(generatorType)) {
                continue;
            }
            ItemBuilder itemBuilder = new ItemBuilder(generatorType.getDrop());
            itemBuilder.setLore(Arrays.asList("", "§7Klik for at sælge alle " + itemBuilder.toItemStack().getItemMeta().getDisplayName(), ""));
            itemBuilder.setName(itemBuilder.toItemStack().getItemMeta().getDisplayName() + " §7x§a" + drops.get(generatorType));
            itemBuilder.setAmount(1);
            GuiItem guiItem = new GuiItem(itemBuilder.toItemStack());
            guiItem.setAction(event -> {
                long generetorAmount = drops.get(generatorType);
                if (generetorAmount > 0) {
                    sell(event.getWhoClicked().getUniqueId(), generatorType, generetorAmount);
                    gui.updatePageItem(event.getSlot(), new ItemStack(Material.AIR));
                    guiItem.setAction(null);
                }
            });
            gui.addItem(guiItem);
        }
        gui.update();
    }

    private long sell(GeneratorType generatorType, long amount) {
        removeDrop(generatorType);
        double price = GeneratorType.DropPrice.valueOf(generatorType.name()).getPrice();
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

    public PaginatedGui getGUI() {
        return gui;
    }
}
