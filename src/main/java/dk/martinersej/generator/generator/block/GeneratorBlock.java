package dk.martinersej.generator.generator.block;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import dk.martinersej.generator.generator.GeneratorItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GeneratorBlock extends GeneratorElement {
    private final Location offSetLocation;
    private GeneratorType generatorType;

    public GeneratorBlock(Location location, UUID owner, GeneratorType generatorType) {
        super(owner, location);
        this.offSetLocation = location.clone().add(0, 1, 0);
        this.generatorType = generatorType;
    }

    public static GeneratorElement deserialize(Location location, UUID owner, String tier) {
        return null;
    }
    public GeneratorType getGeneratorType() {
        return generatorType;
    }

    public void setGeneratorType(GeneratorType generatorType) {
        this.generatorType = generatorType;
    }

    public void drop() {
        GeneratorChest generatorChest = (GeneratorChest) Generator.getGeneratorManager().getUser(getOwner()).getGeneratorChest();
        if (generatorChest != null) {
            generatorChest.addDrop(generatorType);
        } else {
            getLocation().getWorld().dropItemNaturally(offSetLocation, generatorType.getDrop());
        }
    }

    public GeneratorItem createItem() {
        return new GeneratorBlockItem(generatorType);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(getOwner());
    }
}