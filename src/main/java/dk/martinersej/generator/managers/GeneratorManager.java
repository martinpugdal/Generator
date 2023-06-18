package dk.martinersej.generator.managers;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.generator.GeneratorUser;
import dk.martinersej.generator.generator.block.GeneratorBlock;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class GeneratorManager {

    private final Set<GeneratorChest> activeChest = Collections.synchronizedSet(new HashSet<>());
    private final Set<GeneratorChest> chestsToSave = new HashSet<>();

    public GeneratorManager(JavaPlugin plugin, long runInterval) {
        final int[] updateDropsCounter = {0};
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            Set<GeneratorBlock> generatorBlocks = Collections.synchronizedSet(new HashSet<>());
            for (GeneratorUser user : Generator.getUserManager().getActiveUsers()) {
                generatorBlocks.addAll(user.getGenerators());
            }
            for (GeneratorBlock element : generatorBlocks) {
                element.drop();
            }
            for (GeneratorChest chest : activeChest) {
                chest.updateGui();
            }
            updateDropsCounter[0]++;
            if (updateDropsCounter[0] >= 25) {
                updateDropsCounter[0] = 0;
                Generator.getGeneratorManager().updateDrops();
            }
        }, runInterval, runInterval);
    }

    private void updateDrops() {
        Generator.getDBConnectionManager().connect(
                connection -> {
                    try {
                        connection.createStatement().executeUpdate("DELETE FROM generator_chest_drop;");
                        PreparedStatement stmt = connection.prepareStatement(
                                "INSERT INTO generator_chest_drop (chest_id, tier, amount) VALUES (?,?,?)");
                        Set<GeneratorChest> chests = new HashSet<>(chestsToSave);
                        chestsToSave.clear();
                        chests.addAll(activeChest);
                        for (GeneratorChest chest : chests) {
                            for (Map.Entry<GeneratorType, Integer> drops : chest.getDrops().entrySet()) {
                                stmt.setInt(1, chest.getId());
                                stmt.setInt(2, drops.getKey().getTier());
                                stmt.setInt(3, drops.getValue());
                                stmt.addBatch();
                            }
                            System.out.println("Saving drops for chest " + chest.getId() + "...");
                        }
                        stmt.executeBatch();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
        );
    }

    public void loadAll(JavaPlugin plugin) {
        Generator.getDBConnectionManager().connect(
                connection -> {
                    try {
                        PreparedStatement stmt = connection.prepareStatement(
                                "SELECT " +
                                        "    ge.id, " +
                                        "    ge.loc_x, " +
                                        "    ge.loc_y, " +
                                        "    ge.loc_z, " +
                                        "    ge.world, " +
                                        "    ge.owner, " +
                                        "    generator_block.tier as block_tier, " +
                                        "    CASE " +
                                        "        WHEN generator_block.id IS NOT NULL THEN 'generator_block' " +
                                        "        WHEN generator_chest.id IS NOT NULL THEN 'generator_chest' " +
                                        "    END AS type " +
                                        "FROM " +
                                        "    generator_element AS ge " +
                                        "LEFT JOIN " +
                                        "    generator_block ON ge.id = generator_block.id " +
                                        "LEFT JOIN " +
                                        "    generator_chest ON ge.id = generator_chest.id "
                        );
                        ResultSet resultSet = stmt.executeQuery();
                        int count = 0;
                        while (resultSet.next()) {
                            count++;
                            UUID owner = UUID.fromString(resultSet.getString("owner"));
                            int id = resultSet.getInt("id");
                            int loc_x = resultSet.getInt("loc_x");
                            int loc_y = resultSet.getInt("loc_y");
                            int loc_z = resultSet.getInt("loc_z");
                            String world = resultSet.getString("world");
                            Location location = new Location(Bukkit.getWorld(world), loc_x, loc_y, loc_z);

                            String type = resultSet.getString("type");
                            if (type.equalsIgnoreCase("generator_block")) {
                                int tier = resultSet.getInt("block_tier");
                                GeneratorBlock generatorBlock = new GeneratorBlock(location, owner, GeneratorType.getGeneratorType(tier));
                                generatorBlock.setID(id);
                                Generator.getUserManager().getUser(owner).addGeneratorBlock(generatorBlock);
//                                unsafeaddElement(generatorBlock);
                            } else if (type.equalsIgnoreCase("generator_chest")) {
                                GeneratorChest generatorChest = new GeneratorChest(location, owner);
                                generatorChest.setID(id);
                                Generator.getUserManager().getUser(owner).setGeneratorChest(generatorChest);
//                                unsafeaddElement(generatorChest);
                            }
                        }
                        PreparedStatement preparedStatement = connection.prepareStatement("SELECT chest_id, tier, amount FROM generator_chest_drop");
                        ResultSet rs = preparedStatement.executeQuery();
                        while (rs.next()) {
                            int chest_id = rs.getInt("chest_id");
                            int tier = rs.getInt("tier");
                            long amount = rs.getLong("amount");
                            GeneratorChest chest = getCollectorChest(chest_id);
                            if (chest != null) {
                                chest.addDrop(GeneratorType.getGeneratorType(tier), amount);
                            }
                        }
                        System.out.println(plugin.getName() + " Loaded " + count + " generator elements");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private GeneratorChest getCollectorChest(int id) {
        for (GeneratorChest chest : activeChest) {
            if (chest.getId() == id) {
                return chest;
            }
        }
        return null;
    }


    public void saveElement(GeneratorElement element) {
        Generator.getDBConnectionManager().connect((connection) -> {
            try {
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO generator_element (owner, loc_x, loc_y, loc_z, world) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, element.getOwner().toString());
                stmt.setInt(2, element.getLocation().getBlockX());
                stmt.setInt(3, element.getLocation().getBlockY());
                stmt.setInt(4, element.getLocation().getBlockZ());
                stmt.setString(5, element.getLocation().getWorld().getName());
                stmt.executeUpdate();
                int id = stmt.getGeneratedKeys().getInt(1);
                element.setID(id);
                if (element instanceof GeneratorBlock) {
                    stmt = connection.prepareStatement("INSERT INTO generator_block (id, tier) VALUES (?,?)");
                    stmt.setInt(1, id);
                    stmt.setInt(2, ((GeneratorBlock) element).getGeneratorType().getTier());
                    stmt.executeUpdate();
                } else if (element instanceof GeneratorChest) {
                    stmt = connection.prepareStatement("INSERT INTO generator_chest (id) VALUES (?)");
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void deleteElements(Set<GeneratorElement> elements) {
        Generator.getDBConnectionManager().connect(
                (connection -> {
                    try {
                        connection.setAutoCommit(false);
                        PreparedStatement stmt = connection.prepareStatement("DELETE FROM generator_element WHERE id = ?");
                        for (GeneratorElement element : elements) {
                            stmt.setInt(1, element.getId());
                            stmt.addBatch();
                        }
                        stmt.executeBatch();
                        connection.commit();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        try {
                            connection.rollback();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    } finally {
                        try {
                            connection.setAutoCommit(true);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                })
        );
    }

    public void addElement(GeneratorElement element) {
        saveElement(element);
        if (element instanceof GeneratorChest) {
            activeChest.add((GeneratorChest) element);
        }
    }

    public void unsafeaddElement(GeneratorElement element) {
        if (element instanceof GeneratorChest) {
            activeChest.add((GeneratorChest) element);
        }
    }

    public void removeElement(GeneratorElement element) {
        deleteElements(Collections.singleton(element));
        if (element instanceof GeneratorBlock) {
            Generator.getUserManager().getUser(element.getOwner()).removeGeneratorBlock((GeneratorBlock) element);
        } else if (element instanceof GeneratorChest) {
            Generator.getUserManager().getUser(element.getOwner()).setGeneratorChest(null);
            activeChest.remove((GeneratorChest) element);
        }
    }

    public void removeAll(Set<GeneratorElement> elements) {
        deleteElements(elements);
        for (GeneratorElement element : elements) {
            removeElement(element);
        }
    }

    public GeneratorElement getElement(Location location) {
        for (GeneratorUser user : Generator.getUserManager().getUsers().values()) {
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

    public Set<GeneratorElement> getElementsBetweenXandZ(com.intellectualcrafters.plot.object.Location[] plotCorners) {
        com.intellectualcrafters.plot.object.Location min = plotCorners[0];
        com.intellectualcrafters.plot.object.Location max = plotCorners[3];
        Set<GeneratorElement> elements = new HashSet<>();
        for (GeneratorUser user : Generator.getUserManager().getUsers().values()) {
            for (GeneratorBlock element : user.getGenerators()) {
                if (element.getLocation().getBlockX() >= min.getX() && element.getLocation().getBlockX() <= max.getX() && element.getLocation().getBlockZ() >= min.getZ() && element.getLocation().getBlockZ() <= max.getZ()) {
                    elements.add(element);
                }
            }
        }
        return elements;
    }

    public GeneratorBlock getGenerator(Location location) {
        for (GeneratorUser user : Generator.getUserManager().getActiveUsers()) {
            for (GeneratorBlock element : user.getGenerators()) {
                if (element.getLocation().equals(location)) {
                    return element;
                }
            }
        }
        return null;
    }

    public GeneratorChest getCollectorChest(Location location) {
        for (GeneratorUser user : Generator.getUserManager().getUsers().values()) {
            if (user.getGeneratorChest() != null && user.getGeneratorChest().getLocation().equals(location)) {
                return user.getGeneratorChest();
            }
        }
        return null;
    }

    public void updateElement(GeneratorElement generatorElement) {
        if (generatorElement instanceof GeneratorBlock) {
            Generator.getDBConnectionManager().connect(connection -> {
                try {
                    PreparedStatement stmt = connection.prepareStatement("UPDATE generator_block SET tier = ? WHERE id = ?");
                    stmt.setInt(1, ((GeneratorBlock) generatorElement).getGeneratorType().getTier());
                    stmt.setInt(2, generatorElement.getId());
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void addChestToSaving(GeneratorChest chest) {
        activeChest.remove(chest);
        chestsToSave.add(chest);
    }

    public void removeChestFromSaving(GeneratorChest chest) {
        activeChest.add(chest);
        chestsToSave.remove(chest);
    }
}
