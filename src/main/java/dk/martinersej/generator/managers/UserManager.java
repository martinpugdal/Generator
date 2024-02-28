package dk.martinersej.generator.managers;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.User;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserManager {

    private final HashMap<UUID, User> users = new HashMap<>();
    private final Set<User> activeUsers = new HashSet<>();
    private final Set<User> usersToSave = new HashSet<>();

    public UserManager(JavaPlugin plugin, long saveInterval) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            saveUsers(usersToSave);
        }, saveInterval, saveInterval);
    }

    public void loadAll(JavaPlugin plugin, Runnable callback) {
        Generator.getInstance().getDBConnectionManager().connect(connection -> {
            try {
                PreparedStatement stmt = connection.prepareStatement("SELECT uuid, xp, multiplier, generator_slots FROM user;");
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    UUID uuid = UUID.fromString(resultSet.getString(1));
                    double xp = resultSet.getDouble(2);
                    double multiplier = resultSet.getDouble(3);
                    long generatorSlots = resultSet.getLong(4);
                    users.put(uuid, new User(uuid, multiplier, xp, generatorSlots));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println(plugin.getName() + " Loaded " + users.size() + " users");
            callback.run();
        });

    }

    public User getUser(UUID owner) {
        return users.get(owner);
    }

    public void addActiveUser(User user) {
        activeUsers.add(user);
        usersToSave.remove(user);
        if (user.getGeneratorChest() != null) {
            Generator.getInstance().getGeneratorManager().removeChestFromSaving(user.getGeneratorChest());
        }
    }

    public void removeActiveUser(User user) {
        activeUsers.remove(user);
        usersToSave.add(user);
        if (user.getGeneratorChest() != null) {
            Generator.getInstance().getGeneratorManager().addChestToSaving(user.getGeneratorChest());
        }
    }

    public Set<User> getActiveUsers() {
        return activeUsers;
    }

    public void createUser(User user) {
        if(users.containsKey(user.getUUID())) {
            throw new IllegalArgumentException("UUID already exists!");
        }
        Generator.getInstance().getDBConnectionManager().connect((connection) -> {
            try {
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO user (uuid) VALUES (?)");
                stmt.setString(1, user.getUUID().toString());
                stmt.executeUpdate();
                users.put(user.getUUID(), user);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void saveUser(User user) {
        Generator.getInstance().getDBConnectionManager().connect((connection) -> {
            try {
                PreparedStatement stmt = connection.prepareStatement(
                        "UPDATE user "+
                                "SET "+
                                "xp = ?, " +
                                "multiplier = ?, " +
                                "generator_slots = ? " +
                        "WHERE " +
                            "uuid = ?"
                );
                stmt.setDouble(1, user.getXp());
                stmt.setDouble(2, user.getMultiplier());
                stmt.setLong(3, user.getGeneratorSlots());
                stmt.setString(4, user.getUUID().toString());
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void saveUsers(Set<User> users) {
        Set<User> users1 = new HashSet<>(users);
        users1.addAll(activeUsers);
        Generator.getInstance().getDBConnectionManager().connect(
                (connection -> {
                    try {
                        connection.setAutoCommit(false);
                        PreparedStatement stmt = connection.prepareStatement(
                                "UPDATE user "+
                                        "SET "+
                                        "xp = ?, " +
                                        "multiplier = ?, " +
                                        "generator_slots = ? " +
                                        "WHERE " +
                                        "uuid = ?"
                        );
                        for (User user : users1) {
                            stmt.setDouble(1, user.getXp());
                            stmt.setDouble(2, user.getMultiplier());
                            stmt.setLong(3, user.getGeneratorSlots());
                            stmt.setString(4, user.getUUID().toString());
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

    public HashMap<UUID, User> getUsers() {
        return users;
    }
}
