package dk.martinersej.generator.shop;

import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ShopMiscellaneous implements Shop {

    private final ShopItem[] shopItems = Items.values();

    ShopMiscellaneous() {
    }

    @Override
    public ItemStack getCategoryItem() {
        return new ItemBuilder(Material.HOPPER).setName("Misc").toItemStack();
    }

    @Override
    public ShopItem[] getShopItems() {
        return shopItems;
    }

    enum Items implements ShopItem {
        HOPPER(new ItemBuilder(Material.HOPPER).setAmount(64).toItemStack(), 5000, 1);

        private final ItemStack itemStack;
        private final double buyPrice;
        private final double sellPrice;

        Items(ItemStack itemStack, double buyPrice, double sellPrice) {
            this.itemStack = itemStack;
            this.buyPrice = buyPrice;
            this.sellPrice = sellPrice;
        }

        @Override
        public String getName() {
            return StringUtils.formatEnum(this);
        }

        @Override
        public double getBuyPrice() {
            return buyPrice;
        }

        @Override
        public ItemStack getItemStack() {
            return itemStack.clone();
        }

        @Override
        public double getSellPrice() {
            return sellPrice;
        }

        @Override
        public int getAmount() {
            return itemStack.getAmount();
        }
    }
}
