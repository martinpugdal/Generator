package dk.martinersej.generator.generator.block;

import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.awt.*;

public enum GeneratorType {
    GRASS_GENERATOR(
        new ItemBuilder(Material.GRASS, 1).setName("§aGRASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.DIRT, 1).setName("§aGRASS").toItemStack(),
        Color.GREEN
    ),
    STONE_GENERATOR(
        new ItemBuilder(Material.STONE, 1).setName("§7STONE GENERATOR").toItemStack(),
        new ItemBuilder(Material.COBBLESTONE, 1).setName("§7COBBLESTONE").toItemStack(),
        Color.GRAY
    ),
    COALORE_GENERATOR(
        new ItemBuilder(Material.COAL_ORE, 1).setName("§8COAL ORE GENERATOR").toItemStack(),
        new ItemBuilder(Material.COAL, 1).setName("§8COAL").toItemStack(),
        Color.BLACK
    ),
    IRONORE_GENERATOR(
        new ItemBuilder(Material.IRON_ORE, 1).setName("§fIRON ORE GENERATOR").toItemStack(),
        new ItemBuilder(Material.IRON_INGOT, 1).setName("§fIRON INGOT").toItemStack(),
        Color.WHITE
    ),
    GOLDORE_GENERATOR(
        new ItemBuilder(Material.GOLD_ORE, 1).setName("§6GOLD ORE GENERATOR").toItemStack(),
        new ItemBuilder(Material.GOLD_INGOT, 1).setName("§6GOLD INGOT").toItemStack(),
        Color.YELLOW
    ),
    LAPISORE_GENERATOR(
        new ItemBuilder(Material.LAPIS_ORE, 1).setName("§9LAPIS ORE GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 4).setName("§9LAPIS LAZULI").toItemStack(),
        Color.BLUE
    ),
    REDSTONEORE_GENERATOR(
        new ItemBuilder(Material.REDSTONE_ORE, 1).setName("§cREDSTONE ORE GENERATOR").toItemStack(),
        new ItemBuilder(Material.REDSTONE, 1).setName("§cREDSTONE").toItemStack(),
        Color.RED
    ),
    DIAMONDORE_GENERATOR(
        new ItemBuilder(Material.DIAMOND_ORE, 1).setName("§bDIAMOND ORE GENERATOR").toItemStack(),
        new ItemBuilder(Material.DIAMOND, 1).setName("§bDIAMOND").toItemStack(),
        Color.CYAN
    ),
    EMERALDORE_GENERATOR(
        new ItemBuilder(Material.EMERALD_ORE, 1).setName("§aEMERALD ORE GENERATOR").toItemStack(),
        new ItemBuilder(Material.EMERALD, 1).setName("§aEMERALD").toItemStack(),
        Color.GREEN
    ),
    COALBLOCK_GENERATOR(
        new ItemBuilder(Material.COAL_BLOCK, 1).setName("§8COAL BLOCK GENERATOR").toItemStack(),
        new ItemBuilder(Material.COAL, 1).setName("§8COAL").setGlowing(true).toItemStack(),
        Color.BLACK
    ),
    IRONBLOCK_GENERATOR(
        new ItemBuilder(Material.IRON_BLOCK, 1).setName("§fIRON BLOCK GENERATOR").toItemStack(),
        new ItemBuilder(Material.IRON_INGOT, 1).setName("§fIRON INGOT").setGlowing(true).toItemStack(),
        Color.WHITE
    ),
    GOLDBLOCK_GENERATOR(
        new ItemBuilder(Material.GOLD_BLOCK, 1).setName("§6GOLD BLOCK GENERATOR").toItemStack(),
        new ItemBuilder(Material.GOLD_INGOT, 1).setName("§6GOLD INGOT").setGlowing(true).toItemStack(),
        Color.YELLOW
    ),
    LAPISBLOCK_GENERATOR(
        new ItemBuilder(Material.LAPIS_BLOCK, 1).setName("§9LAPIS BLOCK GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 4).setName("§9LAPIS LAZULI").setGlowing(true).toItemStack(),
        Color.BLUE
    ),
    REDSTONEBLOCK_GENERATOR(
        new ItemBuilder(Material.REDSTONE_BLOCK, 1).setName("§cREDSTONE BLOCK GENERATOR").toItemStack(),
        new ItemBuilder(Material.REDSTONE, 1).setName("§cREDSTONE").setGlowing(true).toItemStack(),
        Color.RED
    ),
    DIAMONDBLOCK_GENERATOR(
        new ItemBuilder(Material.DIAMOND_BLOCK, 1).setName("§bDIAMOND BLOCK GENERATOR").toItemStack(),
        new ItemBuilder(Material.DIAMOND, 1).setName("§bDIAMOND").setGlowing(true).toItemStack(),
        Color.CYAN
    ),
    EMERALDBLOCK_GENERATOR(
        new ItemBuilder(Material.EMERALD_BLOCK, 1).setName("§aEMERALD BLOCK GENERATOR").toItemStack(),
        new ItemBuilder(Material.EMERALD, 1).setName("§aEMERALD").setGlowing(true).toItemStack(),
        Color.GREEN
    ),

    WOOLWHITE_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 0).setName("§fWHITE GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("§eWHITE DYE").toItemStack(),
        Color.WHITE
    ),
    WOOLORANGE_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 1).setName("§eORANGE GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 14).setName("§eORANGE DYE").toItemStack(),
        Color.ORANGE
    ),
    WOOLMAGENTA_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 2).setName("§dMAGENTA GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 13).setName("§dMAGENTA DYE").toItemStack(),
        Color.MAGENTA
    ),
    WOOLLIGHTBLUE_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 3).setName("§bLIGHT BLUE GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 12).setName("§bLIGHT BLUE DYE").toItemStack(),
        Color.CYAN
    ),
    WOOLYELLOW_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 4).setName("§eYELLOW GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 11).setName("§eYELLOW DYE").toItemStack(),
        Color.YELLOW
    ),
    WOOLLIME_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 5).setName("§aLIME GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 10).setName("§aLIME DYE").toItemStack(),
        Color.GREEN
    ),
    WOOLPINK_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 6).setName("§dPINK GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 9).setName("§dPINK DYE").toItemStack(),
        Color.PINK
    ),
    WOOLGRAY_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 7).setName("§8GRAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 8).setName("§8GRAY DYE").toItemStack(),
        Color.GRAY
    ),
    WOOLLIGHTGRAY_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 8).setName("§7LIGHT GRAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("§7LIGHT GRAY DYE").toItemStack(),
        Color.LIGHT_GRAY
    ),
    WOOLCYAN_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 9).setName("§3CYAN GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 6).setName("§3CYAN DYE").toItemStack(),
        Color.CYAN
    ),
    WOOLPURPLE_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 10).setName("§5PURPLE GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 5).setName("§5PURPLE DYE").toItemStack(),
        Color.getHSBColor(0.75f, 1f, 0.5f)
    ),
    WOOLBLUE_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 11).setName("§1BLUE GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 4).setName("§1BLUE DYE").toItemStack(),
        Color.BLUE
    ),
    WOOLBROWN_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 12).setName("§6BROWN GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 3).setName("§6BROWN DYE").toItemStack(),
        Color.getHSBColor(0.1f, 1f, 0.5f)
    ),
    WOOLGREEN_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 13).setName("§2GREEN GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 2).setName("§2GREEN DYE").toItemStack(),
        Color.GREEN
    ),
    WOOLRED_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 14).setName("§4RED GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 1).setName("§4RED DYE").toItemStack(),
        Color.RED
    ),
    WOOLBLACK_GENERATOR(
        new ItemBuilder(Material.WOOL, 1).setDurability((short) 15).setName("§0BLACK GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 0).setName("§0BLACK DYE").setGlowing(true).toItemStack(),
        Color.BLACK
    ),

    WHITESTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 0).setName("§fWHITE STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("§fWHITE STAINED CLAY").setGlowing(true).toItemStack(),
        Color.WHITE
    ),
    ORANGESTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 1).setName("§6ORANGE STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 14).setName("§6ORANGE STAINED CLAY").setGlowing(true).toItemStack(),
        Color.ORANGE
    ),
    MAGENTASTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 2).setName("§dMAGENTA STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 13).setName("§dMAGENTA STAINED CLAY").setGlowing(true).toItemStack(),
        Color.MAGENTA
    ),
    LIGHTBLUESTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 3).setName("§bLIGHT BLUE STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 12).setName("§bLIGHT BLUE STAINED CLAY").setGlowing(true).toItemStack(),
        Color.BLUE
    ),
    YELLOWSTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 4).setName("§eYELLOW STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 11).setName("§eYELLOW STAINED CLAY").setGlowing(true).toItemStack(),
        Color.YELLOW
    ),
    LIMESTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 5).setName("§aLIME STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 10).setName("§aLIME STAINED CLAY").setGlowing(true).toItemStack(),
        Color.getHSBColor(0.33f, 1f, 0.5f)
    ),
    PINKSTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 6).setName("§dPINK STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 9).setName("§dPINK STAINED CLAY").setGlowing(true).toItemStack(),
        Color.PINK
    ),
    GRAYSTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 7).setName("§8GRAY STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 8).setName("§8GRAY STAINED CLAY").setGlowing(true).toItemStack(),
        Color.GRAY
    ),
    LIGHTGRAYSTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 8).setName("§7LIGHT GRAY STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 7).setName("§7LIGHT GRAY STAINED CLAY").setGlowing(true).toItemStack(),
        Color.GRAY
    ),
    CYANSTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 9).setName("§3CYAN STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 6).setName("§3CYAN STAINED CLAY").setGlowing(true).toItemStack(),
        Color.getHSBColor(180, 100, 100)
    ),
    PURPLESTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 10).setName("§5PURPLE STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 5).setName("§5PURPLE STAINED CLAY").setGlowing(true).toItemStack(),
        Color.getHSBColor(300, 100, 100)
    ),
    BLUESTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 11).setName("§9BLUE STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 4).setName("§9BLUE STAINED CLAY").setGlowing(true).toItemStack(),
        Color.BLUE
    ),
    BROWNSTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 12).setName("§6BROWN STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 3).setName("§6BROWN STAINED CLAY").setGlowing(true).toItemStack(),
        Color.getHSBColor(6, 56, 20)
    ),
    GREENSTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 13).setName("§2GREEN STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 2).setName("§2GREEN STAINED CLAY").setGlowing(true).toItemStack(),
        Color.GREEN
    ),
    REDSTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 14).setName("§cRED STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 1).setName("§cRED STAINED CLAY").setGlowing(true).toItemStack(),
        Color.RED
    ),
    BLACKSTAINEDCLAY_GENERATOR(
        new ItemBuilder(Material.STAINED_CLAY, 1).setDurability((short) 15).setName("§0BLACK STAINED CLAY GENERATOR").toItemStack(),
        new ItemBuilder(Material.INK_SACK, 1).setDurability((short) 0).setName("§0BLACK STAINED CLAY").setGlowing(true).toItemStack(),
        Color.BLACK
    ),

    WHITESTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 0).setName("§fWHITE STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 0).setName("§fWHITE STAINED GLASS PANE").toItemStack(),
        Color.WHITE
    ),
    ORANGESTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 1).setName("§6ORANGE STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 1).setName("§6ORANGE STAINED GLASS PANE").toItemStack(),
        Color.ORANGE
    ),
    MAGENTASTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 2).setName("§dMAGENTA STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 2).setName("§dMAGENTA STAINED GLASS PANE").toItemStack(),
        Color.MAGENTA
    ),
    LIGHTBLUESTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 3).setName("§bLIGHT BLUE STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 3).setName("§bLIGHT BLUE STAINED GLASS PANE").toItemStack(),
        Color.CYAN
    ),
    YELLOWSTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 4).setName("§eYELLOW STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 4).setName("§eYELLOW STAINED GLASS PANE").toItemStack(),
        Color.YELLOW
    ),
    LIMESTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 5).setName("§aLIME STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 5).setName("§aLIME STAINED GLASS PANE").toItemStack(),
        Color.GREEN
    ),
    PINKSTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 6).setName("§dPINK STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 6).setName("§dPINK STAINED GLASS PANE").toItemStack(),
        Color.PINK
    ),
    GRAYSTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 7).setName("§8GRAY STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 7).setName("§8GRAY STAINED GLASS PANE").toItemStack(),
        Color.GRAY
    ),
    LIGHTGRAYSTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 8).setName("§7LIGHT GRAY STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 8).setName("§7LIGHT GRAY STAINED GLASS PANE").toItemStack(),
        Color.GRAY
    ),
    CYANSTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 9).setName("§3CYAN STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 9).setName("§3CYAN STAINED GLASS PANE").toItemStack(),
        Color.CYAN
    ),
    PURPLESTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 10).setName("§5PURPLE STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 10).setName("§5PURPLE STAINED GLASS PANE").toItemStack(),
        Color.getHSBColor(0.8333333F, 1.0F, 0.5F)
    ),
    BLUESTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 11).setName("§9BLUE STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 11).setName("§9BLUE STAINED GLASS PANE").toItemStack(),
        Color.BLUE
    ),
    BROWNSTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 12).setName("§6BROWN STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 12).setName("§6BROWN STAINED GLASS PANE").toItemStack(),
        Color.getHSBColor(0.08333333F, 1.0F, 0.5F)
    ),
    GREENSTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 13).setName("§2GREEN STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 13).setName("§2GREEN STAINED GLASS PANE").toItemStack(),
        Color.GREEN
    ),
    REDSTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 14).setName("§cRED STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 14).setName("§cRED STAINED GLASS PANE").toItemStack(),
        Color.RED
    ),
    BLACKSTAINEDGLASS_GENERATOR(
        new ItemBuilder(Material.STAINED_GLASS, 1).setDurability((short) 15).setName("§0BLACK STAINED GLASS GENERATOR").toItemStack(),
        new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setDurability((short) 15).setName("§0BLACK STAINED GLASS PANE").toItemStack(),
        Color.BLACK
    ),


    LIMITED1_GENERATOR(
        new ItemBuilder(Material.MELON_BLOCK, 1).setDurability((short) 9).setName("§cLIMITED GENERATOR 1").toItemStack(),
        new ItemBuilder(Material.SPECKLED_MELON, 1).setName("§cLIMITED DYE").setGlowing(true).toItemStack(),
        Color.green,
        false
    ),
    LIMITED2_GENERATOR(
        new ItemBuilder(Material.PACKED_ICE, 1).setDurability((short) 9).setName("§cLIMITED GENERATOR 2").toItemStack(),
        new ItemBuilder(Material.PRISMARINE_CRYSTALS, 1).setName("§cLIMITED DYE").setGlowing(true).toItemStack(),
        Color.white,
        false
    ),
    LIMITED3_GENERATOR(
        new ItemBuilder(Material.HAY_BLOCK, 1).setDurability((short) 9).setName("§cLIMITED GENERATOR 3").toItemStack(),
        new ItemBuilder(Material.WHEAT, 1).setName("§cLIMITED DYE").setGlowing(true).toItemStack(),
        Color.yellow,
        false
    ),

    ;

    private final String name = StringUtils.formatEnum(this);
    private final ItemStack itemStack;
    private final ItemStack itemStackDrop;
    private final Color color;
    private final boolean upgradeAble;

    GeneratorType(ItemStack itemStack, ItemStack itemStackDrop, Color color) {
        this(itemStack, itemStackDrop, color, true);
    }

    GeneratorType(ItemStack itemStack, ItemStack itemStackDrop, Color color, boolean upgradeAble) {
        this.itemStack = new ItemBuilder(itemStack).setNbt("type", "generator").setNbt("tier", String.valueOf(getTier())).toItemStack();
        this.itemStackDrop = itemStackDrop;
        this.color = color;
        this.upgradeAble = upgradeAble;
    }

    public static GeneratorType getGeneratorType(int tier) {
        for (GeneratorType generatorType : GeneratorType.values()) {
            if (generatorType.getTier() == tier) {
                return generatorType;
            }
        }
        return null;
    }

    public double getUpgradePrice() {
        try {
            return UpgradePrice.valueOf(name()).getPrice();
        } catch (IllegalArgumentException e) {
            return -1;
        }
    }

    public int getTier() {
        return ordinal() + 1;
    }

    public boolean isUpgradeAble() {
        return upgradeAble;
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
