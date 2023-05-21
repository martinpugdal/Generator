package dk.martinersej.generator.generator;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GeneratorItem<T extends GeneratorElement> {

    T place(Location loc, UUID owner) {
        return null;
    }

    ItemStack toItemStack() {
        return null;
    }
}
