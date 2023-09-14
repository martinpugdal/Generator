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
    WOOLLIGHTBLUE_GENERATOR(
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
    WOOLLIGHTGRAY_GENERATOR(
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
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 0).setName("§0BLACK DYE").setGlowing(true).toItemStack(),
            32,
            Color.BLACK
    ),

    WHITESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 0).setName("§fWHITE STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 0).setName("§fWHITE STAINED CLAY").setGlowing(true).toItemStack(),
            33,
            Color.WHITE
    ),
    ORANGESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 1).setName("§6ORANGE STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 14).setName("§6ORANGE STAINED CLAY").setGlowing(true).toItemStack(),
            34,
            Color.ORANGE
    ),
    MAGENTASTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 2).setName("§dMAGENTA STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 13).setName("§dMAGENTA STAINED CLAY").setGlowing(true).toItemStack(),
            35,
            Color.MAGENTA
    ),
    LIGHTBLUESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 3).setName("§bLIGHT BLUE STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 12).setName("§bLIGHT BLUE STAINED CLAY").setGlowing(true).toItemStack(),
            36,
            Color.BLUE
    ),
    YELLOWSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 4).setName("§eYELLOW STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 11).setName("§eYELLOW STAINED CLAY").setGlowing(true).toItemStack(),
            37,
            Color.YELLOW
    ),
    LIMESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 5).setName("§aLIME STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 10).setName("§aLIME STAINED CLAY").setGlowing(true).toItemStack(),
            38,
            Color.getHSBColor(0.33f, 1f, 0.5f)
    ),
    PINKSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 6).setName("§dPINK STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 9).setName("§dPINK STAINED CLAY").setGlowing(true).toItemStack(),
            39,
            Color.PINK
    ),
    GRAYSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 7).setName("§8GRAY STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 8).setName("§8GRAY STAINED CLAY").setGlowing(true).toItemStack(),
            40,
            Color.GRAY
    ),
    LIGHTGRAYSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 8).setName("§7LIGHT GRAY STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("§7LIGHT GRAY STAINED CLAY").setGlowing(true).toItemStack(),
            41,
            Color.GRAY
    ),
    CYANSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 9).setName("§3CYAN STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 6).setName("§3CYAN STAINED CLAY").setGlowing(true).toItemStack(),
            42,
            Color.getHSBColor(180, 100, 100)
    ),
    PURPLESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 10).setName("§5PURPLE STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 5).setName("§5PURPLE STAINED CLAY").setGlowing(true).toItemStack(),
            43,
            Color.getHSBColor(300, 100, 100)
    ),
    BLUESTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 11).setName("§9BLUE STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 4).setName("§9BLUE STAINED CLAY").setGlowing(true).toItemStack(),
            44,
            Color.BLUE
    ),
    BROWNSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 12).setName("§6BROWN STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 3).setName("§6BROWN STAINED CLAY").setGlowing(true).toItemStack(),
            45,
            Color.getHSBColor(6, 56, 20)
    ),
    GREENSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 13).setName("§2GREEN STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 2).setName("§2GREEN STAINED CLAY").setGlowing(true).toItemStack(),
            46,
            Color.GREEN
    ),
    REDSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 14).setName("§cRED STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 1).setName("§cRED STAINED CLAY").setGlowing(true).toItemStack(),
            47,
            Color.RED
    ),
    BLACKSTAINEDCLAY_GENERATOR(
            new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 15).setName("§0BLACK STAINED CLAY GENERATOR").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 0).setName("§0BLACK STAINED CLAY").setGlowing(true).toItemStack(),
            48,
            Color.BLACK
    ),

    WHITESTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 0).setName("§fWHITE STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 0).setName("§fWHITE STAINED GLASS PANE").toItemStack(),
            49,
            Color.WHITE
    ),
    ORANGESTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 1).setName("§6ORANGE STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 1).setName("§6ORANGE STAINED GLASS PANE").toItemStack(),
            50,
            Color.ORANGE
    ),
    MAGENTASTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 2).setName("§dMAGENTA STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 2).setName("§dMAGENTA STAINED GLASS PANE").toItemStack(),
            51,
            Color.MAGENTA
    ),
    LIGHTBLUESTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 3).setName("§bLIGHT BLUE STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 3).setName("§bLIGHT BLUE STAINED GLASS PANE").toItemStack(),
            52,
            Color.CYAN
    ),
    YELLOWSTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 4).setName("§eYELLOW STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 4).setName("§eYELLOW STAINED GLASS PANE").toItemStack(),
            53,
            Color.YELLOW
    ),
    LIMESTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 5).setName("§aLIME STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 5).setName("§aLIME STAINED GLASS PANE").toItemStack(),
            54,
            Color.GREEN
    ),
    PINKSTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 6).setName("§dPINK STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 6).setName("§dPINK STAINED GLASS PANE").toItemStack(),
            55,
            Color.PINK
    ),
    GRAYSTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 7).setName("§8GRAY STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 7).setName("§8GRAY STAINED GLASS PANE").toItemStack(),
            56,
            Color.GRAY
    ),
    LIGHTGRAYSTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 8).setName("§7LIGHT GRAY STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 8).setName("§7LIGHT GRAY STAINED GLASS PANE").toItemStack(),
            57,
            Color.GRAY
    ),
    CYANSTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 9).setName("§3CYAN STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 9).setName("§3CYAN STAINED GLASS PANE").toItemStack(),
            58,
            Color.CYAN
    ),
    PURPLESTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 10).setName("§5PURPLE STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 10).setName("§5PURPLE STAINED GLASS PANE").toItemStack(),
            59,
            Color.getHSBColor(0.8333333F, 1.0F, 0.5F)
    ),
    BLUESTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 11).setName("§9BLUE STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 11).setName("§9BLUE STAINED GLASS PANE").toItemStack(),
            60,
            Color.BLUE
    ),
    BROWNSTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 12).setName("§6BROWN STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 12).setName("§6BROWN STAINED GLASS PANE").toItemStack(),
            61,
            Color.getHSBColor(0.08333333F, 1.0F, 0.5F)
    ),
    GREENSTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 13).setName("§2GREEN STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 13).setName("§2GREEN STAINED GLASS PANE").toItemStack(),
            62,
            Color.GREEN
    ),
    REDSTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 14).setName("§cRED STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 14).setName("§cRED STAINED GLASS PANE").toItemStack(),
            63,
            Color.RED
    ),
    BLACKSTAINEDGLASS_GENERATOR(
            new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 15).setName("§0BLACK STAINED GLASS GENERATOR").toItemStack(),
            new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 15).setName("§0BLACK STAINED GLASS PANE").toItemStack(),
            64,
            Color.BLACK
    ),



    LIMITED1_GENERATOR(
            new ItemBuilder(Material.MELON_BLOCK, 1).setDurability((short) 9).setName("§cLIMITED GENERATOR 1").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 3).setName("§cLIMITED DYE").toItemStack(),
            -1,
            Color.green
    ),
    LIMITED2_GENERATOR(
            new ItemBuilder(Material.PACKED_ICE, 1).setDurability((short) 9).setName("§cLIMITED GENERATOR 2").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 3).setName("§cLIMITED DYE").toItemStack(),
            -1,
            Color.green
    ),
    LIMITED3_GENERATOR(
            new ItemBuilder(Material.HAY_BLOCK, 1).setDurability((short) 9).setName("§cLIMITED GENERATOR 3").toItemStack(),
            new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 3).setName("§cLIMITED DYE").toItemStack(),
            -1,
            Color.green
    ),

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

    public String getDisplayName() {
        return itemStack.getItemMeta().getDisplayName();
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
        GRASS_GENERATOR(100),
        STONE_GENERATOR(500),
        COALORE_GENERATOR(1250),
        IRONORE_GENERATOR(2700),
        GOLDORE_GENERATOR(4500),
        LAPISORE_GENERATOR(7500),
        REDSTONEORE_GENERATOR(11000),
        DIAMONDORE_GENERATOR(16000),
        EMERALDORE_GENERATOR(22000),
        COALBLOCK_GENERATOR(28000),
        IRONBLOCK_GENERATOR(35000),
        GOLDBLOCK_GENERATOR(42000),
        LAPISBLOCK_GENERATOR(50000),
        REDSTONEBLOCK_GENERATOR(57500),
        DIAMONDBLOCK_GENERATOR(66000),
        EMERALDBLOCK_GENERATOR(75000),

        WOOLWHITE_GENERATOR(87500),
        WOOLORANGE_GENERATOR(98000),
        WOOLMAGENTA_GENERATOR(110000),
        WOOLLIGHTBLUE_GENERATOR(121000),
        WOOLYELLOW_GENERATOR(133000),
        WOOLLIME_GENERATOR(145000),
        WOOLPINK_GENERATOR(157000),
        WOOLGRAY_GENERATOR(160000),
        WOOLLIGHTGRAY_GENERATOR(172000),
        WOOLCYAN_GENERATOR(185000),
        WOOLPURPLE_GENERATOR(197500),
        WOOLBLUE_GENERATOR(210000),
        WOOLBROWN_GENERATOR(222000),
        WOOLGREEN_GENERATOR(234500),
        WOOLRED_GENERATOR(246000),
        WOOLBLACK_GENERATOR(260000),

        CLAY_GENERATOR(280000),
        WHITESTAINEDCLAY_GENERATOR(305000),
        ORANGESTAINEDCLAY_GENERATOR(320000),
        MAGENTASTAINEDCLAY_GENERATOR(344500),
        LIGHTBLUESTAINEDCLAY_GENERATOR(368500),
        YELLOWSTAINEDCLAY_GENERATOR(395000),
        LIMESTAINEDCLAY_GENERATOR(417500),
        PINKSTAINEDCLAY_GENERATOR(440000),
        GRAYSTAINEDCLAY_GENERATOR(475050),
        LIGHTGRAYSTAINEDCLAY_GENERATOR(500000),
        CYANSTAINEDCLAY_GENERATOR(530000),
        PURPLESTAINEDCLAY_GENERATOR(560000),
        BLUESTAINEDCLAY_GENERATOR(595000),
        BROWNSTAINEDCLAY_GENERATOR(625000),
        GREENSTAINEDCLAY_GENERATOR(650000),
        REDSTAINEDCLAY_GENERATOR(675000),
        BLACKSTAINEDCLAY_GENERATOR(700000),

        WHITESTAINEDGLASS_GENERATOR(745000),
        ORANGESTAINEDGLASS_GENERATOR(790000),
        MAGENTASTAINEDGLASS_GENERATOR(840000),
        LIGHTBLUESTAINEDGLASS_GENERATOR(900000),
        YELLOWSTAINEDGLASS_GENERATOR(965500),
        LIMESTAINEDGLASS_GENERATOR(1030500),
        PINKSTAINEDGLASS_GENERATOR(1090000),
        GRAYSTAINEDGLASS_GENERATOR(1160000),
        LIGHTGRAYSTAINEDGLASS_GENERATOR(1230000),
        CYANSTAINEDGLASS_GENERATOR(1300000),
        PURPLESTAINEDGLASS_GENERATOR(1375000),
        BLUESTAINEDGLASS_GENERATOR(1440000),
        BROWNSTAINEDGLASS_GENERATOR(1500000),
        GREENSTAINEDGLASS_GENERATOR(1650000),
        REDSTAINEDGLASS_GENERATOR(3000000),
        BLACKSTAINEDGLASS_GENERATOR(5000000),

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
        GRASS_GENERATOR(1, 0.5),
        STONE_GENERATOR(3, 1),
        COALORE_GENERATOR(6, 2),
        IRONORE_GENERATOR(10, 2.5),
        GOLDORE_GENERATOR(15, 3),
        LAPISORE_GENERATOR(20, 4.5),
        REDSTONEORE_GENERATOR(25, 5),
        DIAMONDORE_GENERATOR(30, 6),
        EMERALDORE_GENERATOR(40, 7),
        COALBLOCK_GENERATOR(55, 7),
        IRONBLOCK_GENERATOR(60, 8),
        GOLDBLOCK_GENERATOR(80, 9),
        LAPISBLOCK_GENERATOR(100, 10),
        REDSTONEBLOCK_GENERATOR(125, 12),
        DIAMONDBLOCK_GENERATOR(150, 14),
        EMERALDBLOCK_GENERATOR(170, 18),

        WOOLWHITE_GENERATOR(200, 25),
        WOOLORANGE_GENERATOR(240, 30),
        WOOLMAGENTA_GENERATOR(275, 35),
        WOOLLIGHTBLUE_GENERATOR(310, 40),
        WOOLYELLOW_GENERATOR(340, 45),
        WOOLLIME_GENERATOR(370, 50),
        WOOLPINK_GENERATOR(400, 55),
        WOOLGRAY_GENERATOR(420, 60),
        WOOLLIGHTGRAY_GENERATOR(450, 65),
        WOOLCYAN_GENERATOR(490, 70),
        WOOLPURPLE_GENERATOR(530, 75),
        WOOLBLUE_GENERATOR(580, 80),
        WOOLBROWN_GENERATOR(620, 85),
        WOOLGREEN_GENERATOR(670, 90),
        WOOLRED_GENERATOR(740, 95),
        WOOLBLACK_GENERATOR(800, 100),

        CLAY_GENERATOR(850, 100),
        WHITESTAINEDCLAY_GENERATOR(900, 110),
        ORANGESTAINEDCLAY_GENERATOR(940, 120),
        MAGENTASTAINEDCLAY_GENERATOR(990, 130),
        LIGHTBLUESTAINEDCLAY_GENERATOR(1150, 140),
        YELLOWSTAINEDCLAY_GENERATOR(1200, 150),
        LIMESTAINEDCLAY_GENERATOR(1260, 160),
        PINKSTAINEDCLAY_GENERATOR(1310, 170),
        GRAYSTAINEDCLAY_GENERATOR(1350, 180),
        LIGHTGRAYSTAINEDCLAY_GENERATOR(1400, 190),
        CYANSTAINEDCLAY_GENERATOR(1470, 200),
        PURPLESTAINEDCLAY_GENERATOR(1530, 210),
        BLUESTAINEDCLAY_GENERATOR(1580, 220),
        BROWNSTAINEDCLAY_GENERATOR(1640, 230),
        GREENSTAINEDCLAY_GENERATOR(1700, 240),
        REDSTAINEDCLAY_GENERATOR(1765, 250),
        BLACKSTAINEDCLAY_GENERATOR(1800, 260),

        WHITESTAINEDGLASS_GENERATOR(1900, 300),
        ORANGESTAINEDGLASS_GENERATOR(2100, 320),
        MAGENTASTAINEDGLASS_GENERATOR(2210, 340),
        LIGHTBLUESTAINEDGLASS_GENERATOR(2330, 360),
        YELLOWSTAINEDGLASS_GENERATOR(2450, 380),
        LIMESTAINEDGLASS_GENERATOR(2550, 400),
        PINKSTAINEDGLASS_GENERATOR(2670, 420),
        GRAYSTAINEDGLASS_GENERATOR(2800, 440),
        LIGHTGRAYSTAINEDGLASS_GENERATOR(2950, 460),
        CYANSTAINEDGLASS_GENERATOR(3200, 480),
        PURPLESTAINEDGLASS_GENERATOR(3390, 500),
        BLUESTAINEDGLASS_GENERATOR(3510, 520),
        BROWNSTAINEDGLASS_GENERATOR(3840, 540),
        GREENSTAINEDGLASS_GENERATOR(4060, 560),
        REDSTAINEDGLASS_GENERATOR(4210, 580),
        BLACKSTAINEDGLASS_GENERATOR(4500, 600),

        LIMITED1_GENERATOR(5000, 650),
        LIMITED2_GENERATOR(5750, 800),
        LIMITED3_GENERATOR(6500, 950),
        ;

        private final double price;
        private final double xp;

        DropPrice(double price, double xp) {
            this.price = price;
            this.xp = xp;
        }

        public double getPrice() {
            return price;
        }

        public double getXp() {
            return xp;
        }
    }
}
