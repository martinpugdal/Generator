package dk.martinersej.generator.shop;

import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.StringUtils;
import javafx.scene.transform.MatrixType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ShopBlocks implements Shop {

    private final ShopItem[] shopItems = Items.values();

    ShopBlocks() {
    }

    @Override
    public ItemStack getCategoryItem() {
        return new ItemBuilder(Material.BRICK).setName("Blocks").toItemStack();
    }

    @Override
    public ShopItem[] getShopItems() {
        return shopItems;
    }

    enum Items implements ShopItem {
        BRICK(new ItemBuilder(Material.BRICK).setAmount(64).toItemStack(), 100, 1),
        // planks
        TRÆ_PLANK(new ItemBuilder(Material.WOOD).setDurability(0).setAmount(64).toItemStack(), 100, 1),
        MØRK_TRÆ_PLANK(new ItemBuilder(Material.WOOD).setDurability(1).setAmount(64).toItemStack(), 100, 1),
        LYS_TRÆ_PLANK(new ItemBuilder(Material.WOOD).setDurability(2).setAmount(64).toItemStack(), 100, 1),
        MEGET_MØRK_TRÆ_PLANK(new ItemBuilder(Material.WOOD).setDurability(3).setAmount(64).toItemStack(), 100, 1),
        // sand
        SAND(new ItemBuilder(Material.SAND).setAmount(64).toItemStack(), 100, 1),
        RØD_SAND(new ItemBuilder(Material.SAND).setDurability(1).setAmount(64).toItemStack(), 100, 1),
        // sandstone
        SANDSTONE(new ItemBuilder(Material.SANDSTONE).setAmount(64).toItemStack(), 100, 1),
        RED_SANDSTONE(new ItemBuilder(Material.RED_SANDSTONE).setAmount(64).toItemStack(), 100, 1),
        SMOOTH_SANDSTONE(new ItemBuilder(Material.SANDSTONE).setDurability(2).setAmount(64).toItemStack(), 100, 1),
        RED_SMOOTH_SANDSTONE(new ItemBuilder(Material.RED_SANDSTONE).setDurability(2).setAmount(64).toItemStack(), 100, 1),
        SANDSTONE_SLAB(new ItemBuilder(Material.STEP).setDurability(1).setAmount(64).toItemStack(), 100, 1),
        RED_SANDSTONE_SLAB(new ItemBuilder(Material.STONE_SLAB2).setAmount(64).toItemStack(), 100, 1),
        SANDSTONE_STAIR(new ItemBuilder(Material.SANDSTONE_STAIRS).setAmount(64).toItemStack(), 100, 1),
        RED_SANDSTONE_STAIR(new ItemBuilder(Material.RED_SANDSTONE_STAIRS).setAmount(64).toItemStack(), 100, 1),

        // Leaves
        OAKLEAVES(new ItemBuilder(Material.LEAVES).setAmount(64).toItemStack(), 100, 1),
        SPRUCELEAVES(new ItemBuilder(Material.LEAVES).setDurability(1).setAmount(64).toItemStack(), 100, 1),
        BIRCHLEAVES(new ItemBuilder(Material.LEAVES).setDurability(2).setAmount(64).toItemStack(), 100, 1),
        DARKOAKLEAVES(new ItemBuilder(Material.LEAVES_2).setDurability(1).setAmount(64).toItemStack(), 100, 1),

        BOOKSHELF(new ItemBuilder(Material.BOOKSHELF).setAmount(64).toItemStack(), 100, 1),
        SEALANTERN(new ItemBuilder(Material.SEA_LANTERN).setAmount(64).toItemStack(), 100, 1),
        // trapdoor
        WOODENTRAPDOOR(new ItemBuilder(Material.TRAP_DOOR).setAmount(64).toItemStack(), 100, 1),
        IRONTRAPDOOR(new ItemBuilder(Material.IRON_TRAPDOOR).setAmount(64).toItemStack(), 100, 1),
        BEACON(new ItemBuilder(Material.BEACON).setAmount(64).toItemStack(), 100, 1),
        // prismarine
        PRISMARINEBRICKS(new ItemBuilder(Material.PRISMARINE).setDurability(1).setAmount(64).toItemStack(), 100, 1),
        PRISMARINE(new ItemBuilder(Material.PRISMARINE).setAmount(64).toItemStack(), 100, 1),
        DARK_PRISMARINE(new ItemBuilder(Material.PRISMARINE).setDurability(2).setAmount(64).toItemStack(), 100, 1),
        // ice
        ICE(new ItemBuilder(Material.ICE).setAmount(64).toItemStack(), 100, 1),
        PACKED_ICE(new ItemBuilder(Material.PACKED_ICE).setAmount(64).toItemStack(), 100, 1),
        // quartz
        QUARTZ_PILLAR(new ItemBuilder(Material.QUARTZ_BLOCK).setDurability(2).setAmount(64).toItemStack(), 100, 1),
        CHISELED_QUARTZ(new ItemBuilder(Material.QUARTZ_BLOCK).setDurability(1).setAmount(64).toItemStack(), 100, 1),
        QUARTZ_BLOCK(new ItemBuilder(Material.QUARTZ_BLOCK).setAmount(64).toItemStack(), 100, 1),
        QUARTZ_STAIR(new ItemBuilder(Material.QUARTZ_STAIRS).setAmount(64).toItemStack(), 100, 1),
        ;

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
