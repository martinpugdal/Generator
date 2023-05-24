package dk.martinersej.generator.generator;

import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.StringUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum GeneratorType {
    WHITE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 0).setName("§fWHITE GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("§eWHITE DYE").toItemStack()
    ),
    ORANGE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 1).setName("§eORANGE GENERATOR").setNbt("tier", "1").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 14).setName("§eORANGE DYE").toItemStack()
    ),

    ;

    private final String name = StringUtil.formatEnum(this);

    public int getTier() {
        return tier;
    }

    public ItemStack getItemStackDrop() {
        return itemStackDrop;
    }

    private final int tier = this.ordinal() + 1;
    private final ItemStack itemStack;
    private final ItemStack itemStackDrop;

    GeneratorType(ItemStack itemStack, ItemStack itemStackDrop) {
        this.itemStack = new ItemBuilder(itemStack).setNbt("type", "generator").setNbt("tier", String.valueOf(this.tier)).toItemStack();
        this.itemStackDrop = itemStackDrop;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public ItemStack getDrop() {
        return itemStackDrop;
    }

    public enum DropPrice {
        WHITE_GENERATOR(100),
        ORANGE_GENERATOR(200),
        ;

        private final int price;

        DropPrice(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }
}
