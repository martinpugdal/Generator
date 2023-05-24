package dk.martinersej.generator.generator;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public interface GeneratorItem<T extends GeneratorElement> {

    T place(Location loc, UUID owner);

    ItemStack toItemStack();

}

