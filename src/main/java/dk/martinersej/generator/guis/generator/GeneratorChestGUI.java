package dk.martinersej.generator.guis.generator;

import dk.martinersej.generator.generator.block.GeneratorType;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import dk.martinersej.generator.utils.GeneratorUtils;
import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.StringUtils;
import dk.martinersej.generator.utils.gui.PaginatedGui;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneratorChestGUI extends PaginatedGui {

    private final GeneratorChest generatorChest;

    public GeneratorChestGUI(String title, int rows, GeneratorChest generatorChest) {
        super(title, rows);
        this.generatorChest = generatorChest;
        setDecoration();
        updateDropsInChest();
        setPageItems();
        build();
    }

    @Override
    public void rerender() {
        updateDropsInChest();
        setPageItems();
        super.rerender();
    }

    private void setPageItems() {
        ItemBuilder backItem = new ItemBuilder(Material.ARROW).setName("§aForrige side");
        ItemBuilder nextPageItem = new ItemBuilder(Material.ARROW).setName("§aNæste side");

        if (getPageNum() != 1) {
            backItem.setAmount(getPageNum() - 1);
            setItem(5, 2, backItem.toItemStack());
        } else {
            setItem(5, 2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
        }

        int maxPage = getTotalPagesNum();
        if (getPageNum() < maxPage) {
            nextPageItem.setAmount(getPageNum() + 1);
            setItem(5, 8, nextPageItem.toItemStack());
        } else {
            setItem(5, 8, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
        }
    }

    private void setDecoration() {
        setBorder(new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);
        if (event.getCurrentItem().getType().equals(Material.ARROW)) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aNæste side")) {
                if (nextPage()) {
                    rerender();
                }
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aForrige side")) {
                if (previousPage()) {
                    rerender();
                }
            }
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
            super.removeItem(event.getCurrentItem());
            getInventory().remove(event.getCurrentItem());
        }
    }

    public void updateDropsInChest() {
        getPageItems().clear();
        List<GeneratorType> generatorTypes = new ArrayList<>(generatorChest.getDrops().keySet());
        if (generatorTypes.isEmpty()) {
            return;
        }
        Collections.sort(generatorTypes);
        for (GeneratorType generatorType : generatorTypes) {
            int amount = generatorChest.getDrops().get(generatorType);
            ItemBuilder itemBuilder = new ItemBuilder(generatorType.getDrop().clone())
                .setName(StringUtils.formatString(generatorType.getDrop().getItemMeta().getDisplayName()) + " §7x§a" + amount)
                .setLore("§7Klik for at sælge alle " + generatorType.getName(), "§7x§a" + amount)
                .setNbt("GeneratorType", String.valueOf(generatorType.getTier()));
            addItem(itemBuilder.toItemStack());
        }
    }
}
