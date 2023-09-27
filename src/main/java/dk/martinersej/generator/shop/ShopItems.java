package dk.martinersej.generator.shop;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ShopItems {

    GRASS(ShopCategori.BLOCKS, 120),
    ;

    private final ShopCategori shopCategori;
    private final double buyPrice;
    private ItemStack itemStack;

    ShopItems(ShopCategori shopCategori, double buyPrice) {
        this(null, shopCategori, buyPrice);
    }

    ShopItems(ItemStack itemStack, ShopCategori shopCategori, double buyPrice) {
        this.itemStack = itemStack == null ? null : itemStack.clone();
        this.shopCategori = shopCategori;
        this.buyPrice = buyPrice;
    }

    public ShopCategori getShopCategori() {
        return shopCategori;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public ItemStack getItemStack() {
        if (itemStack == null) {
            Material material = Material.getMaterial(this.name());
            if (material != null) {
                itemStack = new ItemStack(material).clone();
            }
        }
        return itemStack;
    }
}
