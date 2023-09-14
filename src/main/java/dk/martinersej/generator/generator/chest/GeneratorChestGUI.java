package dk.martinersej.generator.generator.chest;

import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.utils.GUI;
import dk.martinersej.generator.utils.GeneratorUtils;
import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GeneratorChestGUI extends GUI {

    private final GeneratorChest generatorChest;
    private int currentPage = 1;
    private int maxPage;

    public GeneratorChestGUI(String title, int rows, GeneratorChest generatorChest) {
        super(title, rows);
        this.generatorChest = generatorChest;
        updateGui();
    }

    private void SetPageItems() {
        ItemBuilder backItem = new ItemBuilder(Material.ARROW).setName("§aForrige side");
        ItemBuilder nextPageItem = new ItemBuilder(Material.ARROW).setName("§aNæste side");
        if (currentPage != 1) {
            backItem.setAmount(currentPage);
            setItem(5, 2, backItem.toItemStack());
        } else {
            setItem(5, 2, new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 13).setName(" ").toItemStack());
        }
        if (currentPage < maxPage) {
            nextPageItem.setAmount(currentPage);
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
    public void onGUIClick(InventoryClickEvent event) {
        event.setCancelled(true);

        if (event.getCurrentItem().getType().equals(Material.ARROW)) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aNæste side")) {
                nextPage();
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aForrige side")) {
                previousPage();
            }
            updateGui();
        }
        else {
            String generatorTypeString = GeneratorUtils.getNbt(event.getCurrentItem(), "GeneratorType");
            if (generatorTypeString == null || !StringUtils.isNumeric(generatorTypeString)) {
                return;
            }
            GeneratorType generatorType = GeneratorType.getGeneratorType(Integer.parseInt(generatorTypeString));
            if (generatorType == null) {
                return;
            }
            generatorChest.sell(event.getWhoClicked().getUniqueId(), generatorType, generatorType.getDrop().getAmount());
            updateItem(event.getSlot(), new ItemBuilder(Material.AIR).toItemStack());
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
        SetPageItems();
        maxPage = (int) Math.ceil(generatorChest.getDrops().size() / 27.0);
        Object[] arrayDrops = generatorChest.getDrops().keySet().toArray();
        /*
            TODO: change this code to use for-loop, so the elements will be added in the correct order.
         */
        for (int i = currentPage * 27 - 27; i < generatorChest.getDrops().values().size(); i++) {
            int slot = i - (currentPage * 27 - 27) + 9;
            if (slot >= 27) {
                break;
            }
            GeneratorType generatorType = (GeneratorType) arrayDrops[i];
            if (!generatorChest.getDrops().containsKey(generatorType)) {
                continue;
            }
            int amount = generatorChest.getDrops().get(generatorType);
            ItemBuilder itemBuilder = new ItemBuilder(generatorType.getDrop().clone())
                    .setName(generatorType.getDrop().getItemMeta().getDisplayName() + " §7x§a" + amount)
                    .setLore("§7Klik for at sælge alle " + generatorType.getName(), "§7x§a" + amount)
                    .setNbt("GeneratorType", String.valueOf(generatorType.getTier()));
            setItem(slot, itemBuilder.toItemStack());
        }
        rerender();
    }
}
