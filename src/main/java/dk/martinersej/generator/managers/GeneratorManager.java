package dk.martinersej.generator.managers;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.block.GeneratorBlock;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import dk.martinersej.generator.generator.GeneratorElement;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GeneratorManager {


    private final Set<GeneratorBlock> activeGenerators = Collections.synchronizedSet(new HashSet<>());
    private final Set<GeneratorChest> activeChests = Collections.synchronizedSet(new HashSet<>());

    public GeneratorManager() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Generator.getInstance(), () -> {
            for (GeneratorBlock element : activeGenerators) {
                element.drop();
            }
        }, 20L * 5, 20L * 5);
    }

    public void loadAllGenerators() {
    }


    public void saveToDatabase(GeneratorElement element) {
//        Generator.getDBConnectionManager().connect((connection) -> {
//            try {
//                PreparedStatement stmt = connection.prepareStatement("INSERT INTO conveyorElements (loc_x, loc_y, loc_z, direction, world) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
//                stmt.setInt(1, element.getLocation().getBlockX());
//                stmt.setInt(2, element.getLocation().getBlockY());
//                stmt.setInt(3, element.getLocation().getBlockZ());
//                stmt.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        });
        return;
    }

    private void removeFromDatabase(Set<GeneratorElement> elements) {
        return;
    }

    public void add(GeneratorElement element) {
        if (element instanceof GeneratorBlock) {
            activeGenerators.add((GeneratorBlock) element);
        } else if (element instanceof GeneratorChest) {
            activeChests.add((GeneratorChest) element);
        }
        saveToDatabase(element);
    }

    public void remove(GeneratorElement element) {
        if (element instanceof GeneratorBlock) {
            activeGenerators.remove(element);
        } else if (element instanceof GeneratorChest) {
            activeChests.remove(element);
        }
        removeFromDatabase(Collections.singleton(element));
    }

    public void removeAll(Set<GeneratorElement> elements) {
        for(GeneratorElement element : elements) {
            remove(element);
        }
        removeFromDatabase(elements);
    }

    public GeneratorElement getElement(Location location) {
        for(GeneratorElement element : activeGenerators) {
            if(element.getLocation().equals(location)) {
                return element;
            }
        }
        for(GeneratorElement element : activeChests) {
            if(element.getLocation().equals(location)) {
                return element;
            }
        }
        return null;
    }

    public GeneratorElement getGenerator(Location location) {
        for(GeneratorElement element : activeGenerators) {
            if(element.getLocation().equals(location)) {
                return element;
            }
        }
        return null;
    }

    public GeneratorElement getCollectorChest(UUID owner) {
        for (GeneratorElement element : activeChests) {
            if (element.getOwner().equals(owner)) {
                return element;
            }
        }
        return null;
    }

    public GeneratorElement getCollectorChest(Location location) {
        for (GeneratorElement element : activeChests) {
            if (element.getLocation().equals(location)) {
                return element;
            }
        }
        return null;
    }
}
