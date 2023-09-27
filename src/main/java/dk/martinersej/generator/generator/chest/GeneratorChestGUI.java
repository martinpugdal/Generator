package dk.martinersej.generator.generator.chest;

import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.utils.GeneratorUtils;
import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.StringUtils;
import dk.martinersej.generator.utils.gui.BaseGui;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneratorChestGUI extends BaseGui {

    private final GeneratorChest generatorChest;
    private int currentPage = 1;
    private int maxPage;

    public GeneratorChestGUI(String title, int rows, GeneratorChest generatorChest) {
        super(title, rows);
        this.generatorChest = generatorChest;
        updateGui();
    }

    private void setPageItems() {
        ItemBuilder backItem = new ItemBuilder(Material.ARROW).setName("§aForrige side");
        ItemBuilder nextPageItem = new ItemBuilder(Material.ARROW).setName("§aNæste side");
        if (currentPage != 1) {
            backItem.setAmount(currentPage - 1);
            setItem(5, 2, backItem.toItemStack());
        } else {
            setItem(5, 2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
        }
        maxPage = (int) Math.ceil(generatorChest.getDrops().size() / 27.0);
        if (currentPage < maxPage) {
            nextPageItem.setAmount(currentPage + 1);
            setItem(5, 6, nextPageItem.toItemStack());
        } else {
            setItem(5, 6, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
        }
    }

    private void setDecoration() {
        setRow(new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack(), 0);
        setRow(new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack(), 4);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        if (event.getCurrentItem().getType().equals(Material.ARROW)) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aNæste side")) {
                nextPage();
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aForrige side")) {
                previousPage();
            }
            updateGui();
        } else {
            String generatorTypeString = GeneratorUtils.getNbt(event.getCurrentItem(), "GeneratorType");
            if (generatorTypeString == null || !StringUtils.isNumeric(generatorTypeString)) {
                return;
            }
            GeneratorType generatorType = GeneratorType.getGeneratorType(Integer.parseInt(generatorTypeString));
            if (generatorType == null) {
                return;
            }
            generatorChest.sell(event.getWhoClicked().getUniqueId(), generatorType, generatorChest.getDrops().get(generatorType));
            updateItem(event.getSlot(), new ItemBuilder(Material.AIR).toItemStack());
            setPageItems();
        }
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
        setDecoration();
        setPageItems();
        int slot = 9;
        List<GeneratorType> generatorTypes = new ArrayList<>(generatorChest.getDrops().keySet());
        if (generatorTypes.isEmpty()) {
            return;
        }
        Collections.sort(generatorTypes);
        for (int i = 0; i < generatorTypes.size(); i++) {
            if (slot >= 36) {
                break;
            }
            if (i < (currentPage - 1) * 27) {
                continue;
            }
            GeneratorType generatorType = generatorTypes.get(i);
            int amount = generatorChest.getDrops().get(generatorType);
            ItemBuilder itemBuilder = new ItemBuilder(generatorType.getDrop().clone())
                    .setName(generatorType.getDrop().getItemMeta().getDisplayName() + " §7x§a" + amount)
                    .setLore("§7Klik for at sælge alle " + generatorType.getName(), "§7x§a" + amount)
                    .setNbt("GeneratorType", String.valueOf(generatorType.getTier()));
            setItem(slot, itemBuilder.toItemStack());
            slot++;
        }
        rerender();
    }
}
