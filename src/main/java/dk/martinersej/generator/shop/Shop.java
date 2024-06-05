package dk.martinersej.generator.shop;

import dk.martinersej.generator.utils.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public interface Shop {

    ItemStack getCategoryItem();

    default ItemStack getDisplayItem() {
        return new ItemBuilder(getCategoryItem()).setName(getCategoryItem().getItemMeta().getDisplayName()).setLore("Hej").toItemStack();
    }

    ShopItem[] getShopItems();

    interface ShopItem {

        String getName();

        double getBuyPrice();

        ItemStack getItemStack();

        double getSellPrice();

        int getAmount();

        default boolean isBuyable() {
            return getBuyPrice() > 0;
        }

        default boolean isSellable() {
            return getSellPrice() > 0;
        }

        default ItemStack getSaleItem() {
            return getItemStack().clone();
        }

        default ItemStack getDisplayItem() {
            return new ItemBuilder(getItemStack().clone())
                .setName("§a" + getName()).
                setLore(
                    "§aBuy price: §6" + getBuyPrice(),
                    "§aSell price: §6" + getSellPrice()
                )
                .toItemStack();
        }
    }
}
