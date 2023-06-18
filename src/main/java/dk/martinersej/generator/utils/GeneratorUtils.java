package dk.martinersej.generator.utils;

import dk.martinersej.generator.generator.GeneratorItem;
import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.generator.block.GeneratorBlockItem;
import dk.martinersej.generator.generator.chest.GeneratorChestItem;
import dk.martinersej.generator.generator.chest.GeneratorChestSellStickItem;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class GeneratorUtils {

    public static GeneratorItem<?> fromItemStack(ItemStack is) {
        String type = getNbt(is, "type");
        if (type == null || type.isEmpty()) {
            return null;
        }
        switch (type) {
            case "chest":
                return new GeneratorChestItem();
            case "generator":
                String tier = getNbt(is, "tier");
                return new GeneratorBlockItem(GeneratorType.getGeneratorType(Integer.parseInt(tier)));
            case "sellstick":
                return (GeneratorItem<?>) new GeneratorChestSellStickItem();
            default:
                return null;
        }
    }

    public static String getType(ItemStack is) {
        String type = getNbt(is, "type");
        if (type == null || type.isEmpty()) {
            return null;
        }
        return type;
    }

    private static String getNbt(ItemStack stack, String key) {
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
        NBTTagCompound nbt = null;
        try {
            nbt = nmsStack.getTag();
        } catch (Exception ignored) {
        }
        if (nbt == null) {
            return null;
        }
        return nbt.getString(key);
    }
}

