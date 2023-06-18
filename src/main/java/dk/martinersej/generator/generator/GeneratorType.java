package dk.martinersej.generator.generator;

import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.MathUtils;
import dk.martinersej.generator.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.util.Random;

public enum GeneratorType {
    WHITE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 0).setName("§fWHITE GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("§eWHITE DYE").toItemStack(),
            1,
            Color.WHITE
    ),
    ORANGE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 1).setName("§eORANGE GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 14).setName("§eORANGE DYE").toItemStack(),
            2,
            Color.ORANGE
    ),
    MAGENTA_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 2).setName("§dMAGENTA GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 13).setName("§dMAGENTA DYE").toItemStack(),
            3,
            Color.MAGENTA
    ),
    LIGHT_BLUE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 3).setName("§bLIGHT BLUE GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 12).setName("§bLIGHT BLUE DYE").toItemStack(),
            4,
            Color.CYAN
    ),
    YELLOW_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 4).setName("§eYELLOW GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 11).setName("§eYELLOW DYE").toItemStack(),
            5,
            Color.YELLOW
    ),
    LIME_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 5).setName("§aLIME GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 10).setName("§aLIME DYE").toItemStack(),
            6,
            Color.GREEN
    ),
    PINK_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 6).setName("§dPINK GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 9).setName("§dPINK DYE").toItemStack(),
            7,
            Color.PINK
    ),
    GRAY_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 7).setName("§8GRAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 8).setName("§8GRAY DYE").toItemStack(),
            8,
            Color.GRAY
    ),
    LIGHT_GRAY_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 8).setName("§7LIGHT GRAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("§7LIGHT GRAY DYE").toItemStack(),
            9,
            Color.LIGHT_GRAY
    ),
    //make a limited generator type
    LIMITED_GENERATOR(
            new ItemBuilder(Material.MELON_BLOCK, 1).setDurability((short) 9).setName("§cLIMITED GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 3).setName("§cLIMITED DYE").toItemStack(),
            -1,
            Color.green
    ),

    ;

    private final String name = StringUtils.formatEnum(this);

    public static GeneratorType getGeneratorType(int tier) {
        for (GeneratorType generatorType : GeneratorType.values()) {
            if (generatorType.getTier() == tier) {
                return generatorType;
            }
        }
        return null;
    }

    public static GeneratorType getNextGeneratorType(GeneratorType generatorType) {
        for (GeneratorType type : GeneratorType.values()) {
            if (type.getTier() == generatorType.getTier() + 1) {
                return type;
            }
        }
        return null;
    }

    public int getTier() {
        return tier;
    }

    private final int tier;
    private final ItemStack itemStack;
    private final ItemStack itemStackDrop;
    private final Color color;

    GeneratorType(ItemStack itemStack, ItemStack itemStackDrop, int tier, Color color) {
        this.itemStack = new ItemBuilder(itemStack).setNbt("type", "generator").setNbt("tier", String.valueOf(tier)).toItemStack();
        this.itemStackDrop = itemStackDrop;
        this.tier = tier;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack.clone();
    }

    public ItemStack getDrop() {
        return itemStackDrop.clone();
    }

    public Color getColor() {
        return color;
    }

    public enum UpgradePrice {
        WHITE_GENERATOR(100),
        ORANGE_GENERATOR(200),
        MAGENTA_GENERATOR(300),
        LIGHT_BLUE_GENERATOR(400),
        YELLOW_GENERATOR(500),
        LIME_GENERATOR(600),
        PINK_GENERATOR(700),
        GRAY_GENERATOR(800),
        LIGHT_GRAY_GENERATOR(900)
        ;

        private final int price;

        UpgradePrice(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }

    public enum DropPrice {
        WHITE_GENERATOR(100, 1),
        ORANGE_GENERATOR(200, 4),
        MAGENTA_GENERATOR(300, 6),
        LIGHT_BLUE_GENERATOR(400, 9),
        YELLOW_GENERATOR(500, 12),
        LIME_GENERATOR(600, 14),
        PINK_GENERATOR(700, 15),
        GRAY_GENERATOR(800, 17),
        LIGHT_GRAY_GENERATOR(900, 21),
        LIMITED_GENERATOR(1231, 222)
        ;

        private final double price;
        private final long xp;

        DropPrice(double price, long xp) {
            this.price = price;
            this.xp = xp;
        }

        public double getPrice() {
            return price;
        }

        public long getXp() {
            return xp;
        }
    }
}
