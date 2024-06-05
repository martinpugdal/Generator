package dk.martinersej.generator.guis.generator;

import dk.martinersej.generator.generator.block.GeneratorType;
import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.gui.PaginatedGui;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GenlistGUI extends PaginatedGui {
    public GenlistGUI() {
        super("§6Generatorer", 5);
        setupGui();
        build();
    }

    private void setupGui() {
        ItemStack itemStack = new ItemBuilder(Material.AIR).toItemStack();

        this.setBorder(itemStack);

        this.setItem(3, 1, new ItemBuilder(Material.ARROW).setName("§cForrige side").toItemStack());

        this.setItem(3, 9, new ItemBuilder(Material.ARROW).setName("§aNæste side").toItemStack());

        for (GeneratorType generatorType : GeneratorType.values()) {
            addItem(generatorType.getItemStack());
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
        if (event.getSlot() == 18) {
            previousPage();
        } else if (event.getSlot() == 26) {
            nextPage();
        }
    }
}
