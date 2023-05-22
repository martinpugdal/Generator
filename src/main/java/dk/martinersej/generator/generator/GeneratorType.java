package dk.martinersej.generator.generator;

import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.StringUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum GeneratorType {
    WHITE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 0).setName("§fWHITE GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("varm drop").toItemStack()
    ),
    ORANGE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 1).setName("§eORANGE GENERATOR").setNbt("tier", "1").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 14).setName("varm drop").toItemStack()
    ),

    ;

    private final String name = StringUtil.formatEnum(this);
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
}
