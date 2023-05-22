package dk.martinersej.generator.generator;

import org.bukkit.Location;

import java.util.UUID;

public abstract class GeneratorElement {


    private final UUID owner;
    private final Location location;

    public GeneratorElement(UUID owner, Location location) {
        this.owner = owner;
        this.location = location;
    }

    public UUID getOwner() {
        return owner;
    }

    public Location getLocation() {
        return location;
    }

    public abstract GeneratorItem<GeneratorElement> createItem();
}
