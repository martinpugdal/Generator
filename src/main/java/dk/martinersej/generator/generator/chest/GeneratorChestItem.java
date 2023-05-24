package dk.martinersej.generator.generator.chest;

import dk.martinersej.generator.generator.GeneratorItem;
import dk.martinersej.generator.utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GeneratorChestItem implements GeneratorItem<GeneratorChest> {

    @Override
    public GeneratorChest place(Location loc, UUID owner) {
        return new GeneratorChest(loc, owner);
    }

    @Override
    public ItemStack toItemStack() {
        return new ItemBuilder(Material.ENDER_CHEST)
                .setName("§6Generator Chest")
                .setLore("§7Place this chest to collect drops from your generators")
                .setNbt("type", "chest")
                .toItemStack();
    }
}
