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

import java.util.UUID;
import java.util.WeakHashMap;

public class GeneratorChest extends GeneratorElement {
    private final Location location;
    private final WeakHashMap<GeneratorType, Integer> drops = new WeakHashMap<>();
    private final Gui gui = new Gui(5, "Generator Chest");

    public GeneratorChest(Location location, UUID owner) {
        super(owner, location);
        this.location = location;
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

    public void removeDrop(GeneratorType generatorType) {
        drops.remove(generatorType);
    }

    public void openGUI(Player player) {
        gui.open(player);
    }

    public void updateGui() {

        for (GeneratorType generatorType : GeneratorType.values()) {
            gui.removeItem(generatorType.getTier() + 8);
            if (!drops.containsKey(generatorType)) {
                continue;
            }
            GuiItem guiItem = new GuiItem(generatorType.getDrop().clone());
            ItemMeta itemMeta = guiItem.getItemStack().getItemMeta().clone();
            itemMeta.setDisplayName(itemMeta.getDisplayName() + " §7x§a" + drops.get(generatorType));
            guiItem.getItemStack().setItemMeta(itemMeta);
            guiItem.getItemStack().setAmount(1);
            guiItem.setAction(event -> {
                if (drops.get(generatorType) > 0) {
                    sell(generatorType);
                    removeDrop(generatorType);
                    guiItem.setItemStack(new ItemStack(Material.AIR));
                }
                gui.updateItem(event.getSlot(), guiItem);
            });
            gui.addItem(guiItem);
        }
        gui.update();
    }

    private void sell(GeneratorType generatorType) {
        int amount = drops.get(generatorType);
        int price = GeneratorType.DropPrice.valueOf(generatorType.name()).getPrice();
        double multiplier = Generator.getGeneratorManager().getUser(getOwner()).getMultiplier();
        double total = price * amount * multiplier;
        Player player = Bukkit.getPlayer(getOwner());
        VaultHook.addBalance(player, total);
        player.sendMessage("§aDu solgte for §e" + total + " §amed en multiplier på §e" + multiplier + " §a!");

    }

    public WeakHashMap<GeneratorType, Integer> getDrops() {
        return drops;
    }
}
