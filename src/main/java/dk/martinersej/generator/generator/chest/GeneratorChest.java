package dk.martinersej.generator.generator.chest;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.generator.GeneratorItem;
import dk.martinersej.generator.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.WeakHashMap;

public class GeneratorChest extends GeneratorElement {
    private final Location location;
    private final WeakHashMap<GeneratorType, Integer> drops;

    public GeneratorChest(Location location, UUID owner) {
        super(owner, location);
        this.location = location;
        this.drops = new WeakHashMap<>();
    }

    @Override
    public GeneratorItem createItem() {
        return new GeneratorChestItem();
    }

    public void addDrop(GeneratorType generatorType) {
        drops.putIfAbsent(generatorType, generatorType.getDrop().getAmount());
        drops.computeIfPresent(generatorType, (k, v) -> v + generatorType.getDrop().getAmount());
    }

    public void removeDrop(GeneratorType generatorType) {
        drops.remove(generatorType);
    }

    public void openGUI(Player player) {
        Gui mainGui = new Gui(6, "Generator Chest");
        mainGui.disableAllInteractions();
        GuiItem filler = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 15).setName(" ").toItemStack());
        mainGui.setDefaultClickAction(event -> event.setCancelled(true));
        mainGui.getFiller().fillBottom(filler);
        mainGui.getFiller().fillTop(filler);
        for (GeneratorType generatorType : GeneratorType.values()) {
            if (!drops.containsKey(generatorType)) {
                continue;
            }
            mainGui.addItem(new GuiItem(generatorType.getDrop()));
        }
        mainGui.open(player);
    }

    public WeakHashMap<GeneratorType, Integer> getDrops() {
        return drops;
    }
}
