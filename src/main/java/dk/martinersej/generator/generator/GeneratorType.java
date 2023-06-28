package dk.martinersej.generator.generator;

import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.awt.*;

public enum GeneratorType {
    GRASS_GENERATOR(
            new ItemBuilder(Material.GRASS, 1).setName("§aGRASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.DIRT, 1).setName("§aGRASS").toItemStack(),
            1,
            Color.GREEN
    ),
    STONE_GENERATOR(
            new ItemBuilder(Material.STONE, 1).setName("§7STONE GENERATOR").toItemStack(),
            new ItemBuilder(Material.COBBLESTONE, 1).setName("§7COBBLESTONE").toItemStack(),
            2,
            Color.GRAY
    ),
    COALORE_GENERATOR(
            new ItemBuilder(Material.COAL_ORE, 1).setName("§8COAL ORE GENERATOR").toItemStack(),
            new ItemBuilder(Material.COAL, 1).setName("§8COAL").toItemStack(),
            3,
            Color.BLACK
    ),
    IRONORE_GENERATOR(
            new ItemBuilder(Material.IRON_ORE, 1).setName("§fIRON ORE GENERATOR").toItemStack(),
            new ItemBuilder(Material.IRON_INGOT, 1).setName("§fIRON INGOT").toItemStack(),
            4,
            Color.WHITE
    ),
    GOLDORE_GENERATOR(
            new ItemBuilder(Material.GOLD_ORE, 1).setName("§6GOLD ORE GENERATOR").toItemStack(),
            new ItemBuilder(Material.GOLD_INGOT, 1).setName("§6GOLD INGOT").toItemStack(),
            5,
            Color.YELLOW
    ),
    LAPISORE_GENERATOR(
            new ItemBuilder(Material.LAPIS_ORE, 1).setName("§9LAPIS ORE GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 4).setName("§9LAPIS LAZULI").toItemStack(),
            6,
            Color.BLUE
    ),
    REDSTONEORE_GENERATOR(
            new ItemBuilder(Material.REDSTONE_ORE, 1).setName("§cREDSTONE ORE GENERATOR").toItemStack(),
            new ItemBuilder(Material.REDSTONE, 1).setName("§cREDSTONE").toItemStack(),
            7,
            Color.RED
    ),
    DIAMONDORE_GENERATOR(
            new ItemBuilder(Material.DIAMOND_ORE, 1).setName("§bDIAMOND ORE GENERATOR").toItemStack(),
            new ItemBuilder(Material.DIAMOND, 1).setName("§bDIAMOND").toItemStack(),
            8,
            Color.CYAN
    ),
    EMERALDORE_GENERATOR(
            new ItemBuilder(Material.EMERALD_ORE, 1).setName("§aEMERALD ORE GENERATOR").toItemStack(),
            new ItemBuilder(Material.EMERALD, 1).setName("§aEMERALD").toItemStack(),
            9,
            Color.GREEN
    ),
    COALBLOCK_GENERATOR(
            new ItemBuilder(Material.COAL_BLOCK, 1).setName("§8COAL BLOCK GENERATOR").toItemStack(),
            new ItemBuilder(Material.COAL, 1).setName("§8COAL").setGlowing(true).toItemStack(),
            10,
            Color.BLACK
    ),
    IRONBLOCK_GENERATOR(
            new ItemBuilder(Material.IRON_BLOCK, 1).setName("§fIRON BLOCK GENERATOR").toItemStack(),
            new ItemBuilder(Material.IRON_INGOT, 1).setName("§fIRON INGOT").setGlowing(true).toItemStack(),
            11,
            Color.WHITE
    ),
    GOLDBLOCK_GENERATOR(
            new ItemBuilder(Material.GOLD_BLOCK, 1).setName("§6GOLD BLOCK GENERATOR").toItemStack(),
            new ItemBuilder(Material.GOLD_INGOT, 1).setName("§6GOLD INGOT").setGlowing(true).toItemStack(),
            12,
            Color.YELLOW
    ),
    LAPISBLOCK_GENERATOR(
            new ItemBuilder(Material.LAPIS_BLOCK, 1).setName("§9LAPIS BLOCK GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 4).setName("§9LAPIS LAZULI").setGlowing(true).toItemStack(),
            13,
            Color.BLUE
    ),
    REDSTONEBLOCK_GENERATOR(
            new ItemBuilder(Material.REDSTONE_BLOCK, 1).setName("§cREDSTONE BLOCK GENERATOR").toItemStack(),
            new ItemBuilder(Material.REDSTONE, 1).setName("§cREDSTONE").setGlowing(true).toItemStack(),
            14,
            Color.RED
    ),
    DIAMONDBLOCK_GENERATOR(
            new ItemBuilder(Material.DIAMOND_BLOCK, 1).setName("§bDIAMOND BLOCK GENERATOR").toItemStack(),
            new ItemBuilder(Material.DIAMOND, 1).setName("§bDIAMOND").setGlowing(true).toItemStack(),
            15,
            Color.CYAN
    ),
    EMERALDBLOCK_GENERATOR(
            new ItemBuilder(Material.EMERALD_BLOCK, 1).setName("§aEMERALD BLOCK GENERATOR").toItemStack(),
            new ItemBuilder(Material.EMERALD, 1).setName("§aEMERALD").setGlowing(true).toItemStack(),
            16,
            Color.GREEN
    ),

    WOOLWHITE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 0).setName("§fWHITE GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("§eWHITE DYE").toItemStack(),
            17,
            Color.WHITE
    ),
    WOOLORANGE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 1).setName("§eORANGE GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 14).setName("§eORANGE DYE").toItemStack(),
            18,
            Color.ORANGE
    ),
    WOOLMAGENTA_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 2).setName("§dMAGENTA GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 13).setName("§dMAGENTA DYE").toItemStack(),
            19,
            Color.MAGENTA
    ),
    WOOLLIGHT_BLUE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 3).setName("§bLIGHT BLUE GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 12).setName("§bLIGHT BLUE DYE").toItemStack(),
            20,
            Color.CYAN
    ),
    WOOLYELLOW_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 4).setName("§eYELLOW GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 11).setName("§eYELLOW DYE").toItemStack(),
            21,
            Color.YELLOW
    ),
    WOOLLIME_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 5).setName("§aLIME GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 10).setName("§aLIME DYE").toItemStack(),
            22,
            Color.GREEN
    ),
    WOOLPINK_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 6).setName("§dPINK GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 9).setName("§dPINK DYE").toItemStack(),
            23,
            Color.PINK
    ),
    WOOLGRAY_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 7).setName("§8GRAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 8).setName("§8GRAY DYE").toItemStack(),
            24,
            Color.GRAY
    ),
    WOOLLIGHT_GRAY_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 8).setName("§7LIGHT GRAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("§7LIGHT GRAY DYE").toItemStack(),
            25,
            Color.LIGHT_GRAY
    ),
    WOOLCYAN_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 9).setName("§3CYAN GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 6).setName("§3CYAN DYE").toItemStack(),
            26,
            Color.CYAN
    ),
    WOOLPURPLE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 10).setName("§5PURPLE GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 5).setName("§5PURPLE DYE").toItemStack(),
            27,
            Color.getHSBColor(0.75f, 1f, 0.5f)
    ),
    WOOLBLUE_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 11).setName("§1BLUE GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 4).setName("§1BLUE DYE").toItemStack(),
            28,
            Color.BLUE
    ),
    WOOLBROWN_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 12).setName("§6BROWN GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 3).setName("§6BROWN DYE").toItemStack(),
            29,
            Color.getHSBColor(0.1f, 1f, 0.5f)
    ),
    WOOLGREEN_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 13).setName("§2GREEN GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 2).setName("§2GREEN DYE").toItemStack(),
            30,
            Color.GREEN
    ),
    WOOLRED_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 14).setName("§4RED GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 1).setName("§4RED DYE").toItemStack(),
            31,
            Color.RED
    ),
    WOOLBLACK_GENERATOR(
            new ItemBuilder(Material.WOOL, 1).setDurability((short) 15).setName("§0BLACK GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 0).setName("§0BLACK DYE").toItemStack(),
            32,
            Color.BLACK
    ),

    CLAY_GENERATOR(
            new ItemBuilder(Material.CLAY, 1).setName("§fCLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 0).setName("§fCLAY").toItemStack(),
            33,
            Color.WHITE
    ),
    WHITESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 0).setName("§fWHITE STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 0).setName("§fWHITE STAINED CLAY").toItemStack(),
            34,
            Color.WHITE
    ),
    ORANGESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 1).setName("§6ORANGE STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 14).setName("§6ORANGE STAINED CLAY").toItemStack(),
            35,
            Color.ORANGE
    ),
    MAGENTASTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 2).setName("§dMAGENTA STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 13).setName("§dMAGENTA STAINED CLAY").toItemStack(),
            36,
            Color.MAGENTA
    ),
    LIGHTBLUESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 3).setName("§bLIGHT BLUE STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 12).setName("§bLIGHT BLUE STAINED CLAY").toItemStack(),
            37,
            Color.BLUE
    ),
    YELLOWSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 4).setName("§eYELLOW STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 11).setName("§eYELLOW STAINED CLAY").toItemStack(),
            38,
            Color.YELLOW
    ),
    LIMESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 5).setName("§aLIME STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 10).setName("§aLIME STAINED CLAY").toItemStack(),
            39,
            Color.GREEN
    ),
    PINKSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 6).setName("§dPINK STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 9).setName("§dPINK STAINED CLAY").toItemStack(),
            40,
            Color.PINK
    ),
    GRAYSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 7).setName("§8GRAY STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 8).setName("§8GRAY STAINED CLAY").toItemStack(),
            41,
            Color.GRAY
    ),
    LIGHTGRAYSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 8).setName("§7LIGHT GRAY STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("§7LIGHT GRAY STAINED CLAY").toItemStack(),
            42,
            Color.GRAY
    ),
    CYANSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 9).setName("§3CYAN STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 6).setName("§3CYAN STAINED CLAY").toItemStack(),
            43,
            Color.getHSBColor(180, 100, 100)
    ),
    PURPLESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 10).setName("§5PURPLE STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 5).setName("§5PURPLE STAINED CLAY").toItemStack(),
            44,
            Color.getHSBColor(300, 100, 100)
    ),
    BLUESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 11).setName("§9BLUE STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 4).setName("§9BLUE STAINED CLAY").toItemStack(),
            45,
            Color.BLUE
    ),
    BROWNSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 12).setName("§6BROWN STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 3).setName("§6BROWN STAINED CLAY").toItemStack(),
            46,
            Color.getHSBColor(30, 100, 100)
    ),
    GREENSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 13).setName("§2GREEN STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 2).setName("§2GREEN STAINED CLAY").toItemStack(),
            47,
            Color.GREEN
    ),
    REDSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 14).setName("§cRED STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 1).setName("§cRED STAINED CLAY").toItemStack(),
            48,
            Color.RED
    ),
    BLACKSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 15).setName("§0BLACK STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 0).setName("§0BLACK STAINED CLAY").toItemStack(),
            49,
            Color.BLACK
    ),


    //make a limited generator type
//    LIMITED_GENERATOR(
//            new ItemBuilder(Material.MELON_BLOCK, 1).setDurability((short) 9).setName("§cLIMITED GENERATOR").toItemStack(),
//            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 3).setName("§cLIMITED DYE").toItemStack(),
//            -1,
//            Color.green
//    ),

    ;

    private final String name = StringUtils.formatEnum(this);
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
        // add all from GeneratorType here
        GRASS_GENERATOR(100),
        STONE_GENERATOR(100),
        COALORE_GENERATOR(100),
        IRONORE_GENERATOR(100),
        GOLDORE_GENERATOR(100),
        LAPISORE_GENERATOR(100),
        REDSTONEORE_GENERATOR(100),
        DIAMONDORE_GENERATOR(100),
        EMERALDORE_GENERATOR(100),
        COALBLOCK_GENERATOR(100),
        IRONBLOCK_GENERATOR(100),
        GOLDBLOCK_GENERATOR(100),
        LAPISBLOCK_GENERATOR(100),
        REDSTONEBLOCK_GENERATOR(100),
        DIAMONDBLOCK_GENERATOR(100),
        EMERALDBLOCK_GENERATOR(100),
        WOOLWHITE_GENERATOR(100),
        WOOLORANGE_GENERATOR(100),
        WOOLMAGENTA_GENERATOR(100),
        WOOLLIGHTBLUE_GENERATOR(100),
        WOOLYELLOW_GENERATOR(100),
        WOOLLIME_GENERATOR(100),
        WOOLPINK_GENERATOR(100),
        WOOLGRAY_GENERATOR(100),;


        private final int price;

        UpgradePrice(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }

    public enum DropPrice {
        // add all from GeneratorType here
        GRASS_GENERATOR(100, 100),
        STONE_GENERATOR(100, 100),
        COALORE_GENERATOR(100, 100),
        IRONORE_GENERATOR(100, 100),
        GOLDORE_GENERATOR(100, 100),;

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
