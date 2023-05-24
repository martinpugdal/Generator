package dk.martinersej.generator.managers;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorUser;
import dk.martinersej.generator.generator.block.GeneratorBlock;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import dk.martinersej.generator.generator.GeneratorElement;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.*;

public class GeneratorManager {

    private final Set<GeneratorChest> activeChest = Collections.synchronizedSet(new HashSet<>());
    private final Set<GeneratorUser> activeUsers = new HashSet<>();

    public GeneratorManager() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Generator.getInstance(), () -> {
            for (GeneratorUser user : activeUsers) {
                for (GeneratorBlock element : user.getGenerators()) {
                    element.drop();
                }
            }
        }, 20L * 5, 20L * 5);
    }

    public void loadAll() {
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

    public void addElement(GeneratorElement element) {
        if (element instanceof GeneratorBlock) {
            getUser(element.getOwner()).addGeneratorBlock((GeneratorBlock) element);
        } else if (element instanceof GeneratorChest) {
            getUser(element.getOwner()).setGeneratorChest((GeneratorChest) element);
            activeChest.add((GeneratorChest) element);
        }
        saveToDatabase(element);
    }

    public GeneratorUser getUser(UUID owner) {
        for (GeneratorUser user : activeUsers) {
            if (user.getUUID().equals(owner)) {
                return user;
            }
        }
        return null;
    }

    public void removeElement(GeneratorElement element) {
        if (element instanceof GeneratorBlock) {
            getUser(element.getOwner()).removeGeneratorBlock((GeneratorBlock) element);
        } else if (element instanceof GeneratorChest) {
            getUser(element.getOwner()).setGeneratorChest(null);
            activeChest.remove((GeneratorChest) element);
        }
        removeFromDatabase(Collections.singleton(element));
    }

    public void removeAll(Set<GeneratorElement> elements) {
        for(GeneratorElement element : elements) {
            removeElement(element);
        }
        removeFromDatabase(elements);
    }

    public GeneratorElement getElement(Location location) {
        for (GeneratorUser user : activeUsers) {
            if (user.getGeneratorChest() != null && user.getGeneratorChest().getLocation().equals(location)) {
                return user.getGeneratorChest();
            }
            for (GeneratorBlock element : user.getGenerators()) {
                if (element.getLocation().equals(location)) {
                    return element;
                }
            }
        }
        return null;
    }

    public GeneratorElement getElementsBetweenXandZ(HashMap<Integer, Location[]> plotCorners) {

        return null;
    }

    public GeneratorElement getGenerator(Location location) {
        for (GeneratorUser user : activeUsers) {
            for (GeneratorBlock element : user.getGenerators()) {
                if (element.getLocation().equals(location)) {
                    return element;
                }
            }
        }
        return null;
    }

    public GeneratorElement getCollectorChest(Location location) {
        for(GeneratorUser user : activeUsers) {
            if(user.getGeneratorChest() != null && user.getGeneratorChest().getLocation().equals(location)) {
                return user.getGeneratorChest();
            }
        }
        return null;
    }

    public void addUser(GeneratorUser user) {
        activeUsers.add(user);
    }
}
