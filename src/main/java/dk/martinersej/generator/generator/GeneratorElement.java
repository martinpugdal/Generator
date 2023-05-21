package dk.martinersej.generator.generator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public abstract class GeneratorElement {

    private final Location location;


    public GeneratorElement(Location location) {
        this.location = location.clone();
    }

    public static GeneratorElement deserialize(Location location, UUID owner, String tier) {
        return null;
    }

    public Location getLocation() {
        return location;
    }

    public abstract String getTier();

    public abstract void setTier(String tier);

    public abstract UUID getOwner();

    public abstract void setOwner(UUID owner);

    public void drop() {
        Bukkit.getWorld(location.getWorld().getName()).dropItemNaturally(location, new ItemStack(Material.STONE, 1));
    }
}
