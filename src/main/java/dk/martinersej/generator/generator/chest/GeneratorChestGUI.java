package dk.martinersej.generator.generator.chest;

import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.utils.GUI;
import dk.martinersej.generator.utils.ItemBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.WeakHashMap;

public class GeneratorChestGUI extends GUI {

    private final WeakHashMap<GeneratorType, Integer> drops;
    private int currentPage = 0;

    public GeneratorChestGUI(String title, int rows, WeakHashMap<GeneratorType, Integer> drops) {
        super(title, rows);
        this.drops = drops;
    }

    @Override
    public void onGUIClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void nextPage() {
        currentPage++;
    }

    public void previousPage() {
        if (currentPage > 0) {
            currentPage--;
        }
    }

    public void updateGui() {
        clearItems();
        for (int i = 0; i < GeneratorType.values().length; i++) {
            if (!drops.containsKey(GeneratorType.values()[i])) {
                continue;
            }
            GeneratorType generatorType = GeneratorType.values()[i];
            ItemBuilder itemBuilder = new ItemBuilder(generatorType.getDrop());
            itemBuilder.setLore(Arrays.asList("", "§7Klik for at sælge alle " + itemBuilder.getName(), ""));
            itemBuilder.setName(itemBuilder.getName() + " §7x§a" + drops.get(generatorType));
//            itemBuilder.setAmount(1); // always 1
            int slot = (i + 9) * getCurrentPage();
            setItem(i, itemBuilder.toItemStack());
        }
    }
}
