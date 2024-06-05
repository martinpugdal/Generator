package dk.martinersej.generator.guis.shop;

import dk.martinersej.generator.shop.ShopCategori;
import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.gui.BaseGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class ShopGUI extends BaseGui {

    private Map<Integer, ShopCategori> categories = new HashMap<>();

    public ShopGUI() {
        super("Shop", 5);

        // yellow stained glass
        ItemStack borderItem = new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 4).setName(" ").toItemStack();

        // fill all slots in the inventory with this borderItem
        for (int slot = 0; slot < getInventory().getSize(); slot++) {
            setItem(slot, borderItem);
        }




        //setup categories in the shop
        setupCategories();

        build();
    }

    private void setupCategories() {
        int index = 20;
        for (ShopCategori shopCategori : ShopCategori.values()) {
            ItemStack categoryItem = shopCategori.getShop().getDisplayItem();
            setItem(index, categoryItem);
            categories.put(index, shopCategori);

            // remove glass from north, east, west and south of the itemstack we placed here
            getRidOfGlassAroundSlot(index);

            index += 2;
        }
    }

    private void getRidOfGlassAroundSlot(int index) {
        int northSlot = index - 9;
        int eastSlot = index - 1;
        int westSlot = index + 1;
        int southSlot = index + 9;
        int[] slotsToRemoveFrom = new int[]{northSlot, eastSlot, westSlot, southSlot};

        for (int slot : slotsToRemoveFrom) {
            setItem(slot, null);
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true);

        if (categories.containsKey(event.getSlot())) {
            ShopCategori shopCategori = categories.get(event.getSlot());
            new CategoryShopGUI(shopCategori).open((Player) event.getWhoClicked());
        }
    }
}
