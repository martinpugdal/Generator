package dk.martinersej.generator.generator.guis;

import dk.martinersej.generator.generator.block.GeneratorType;
import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.gui.PaginatedGui;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GenlistGUI extends PaginatedGui {
    public GenlistGUI() {
        super(5, "§6Generatorer");
        setupGui();
        build();
    }

    private void setupGui() {
        ItemStack itemStack = new ItemBuilder(Material.ARROW).toItemStack();

        this.setBorder(itemStack);

        for (GeneratorType generatorType : GeneratorType.values()) {
            this.addItem(generatorType.getItemStack());
        }

        ItemStack backItem = new ItemBuilder(Material.ARROW).setName("Forrige side").toItemStack();
        this.setItem(3, 1, backItem);

        ItemStack nextPageItem = new ItemBuilder(Material.ARROW).setName("Næste side").toItemStack();
        this.setItem(3, 9, nextPageItem);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
        if (event.getSlot() == 3) {
            previousPage();
        } else if (event.getSlot() == 9) {
            nextPage();
        }
    }
}
