package dk.martinersej.generator.generator;

import org.bukkit.Location;

import java.util.UUID;

public abstract class GeneratorElement {

    private final UUID owner;
    private final Location location;
    private int id;

    public GeneratorElement(UUID owner, Location location) {
        this.owner = owner;
        this.location = location.clone();
    }

    public UUID getOwner() {
        return owner;
    }

    public Location getLocation() {
        return location;
    }

    public abstract GeneratorItem<GeneratorElement> createItem();

    public void setID(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
