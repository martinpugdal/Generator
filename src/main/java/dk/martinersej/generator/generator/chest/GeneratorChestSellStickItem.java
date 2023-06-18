package dk.martinersej.generator.generator.chest;

import dk.martinersej.generator.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GeneratorChestSellStickItem {

    public ItemStack toItemStack() {
        return new ItemBuilder(Material.STICK)
            .setName("§6Sell Stick")
            .setLore("§7Right click a generator to sell it")
            .setNbt("type", "sellstick")
            .toItemStack();
    }
}
