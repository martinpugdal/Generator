package dk.martinersej.generator.managers;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.Team;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TeamManager {

    private final HashMap<String, Team> teams = new HashMap<>();
    private final Set<Team> teamsToSave = new HashSet<>();

    public TeamManager(JavaPlugin plugin, long saveInterval) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            saveTeams(teamsToSave);
        }, saveInterval, saveInterval);
    }

    //    public void loadAll(JavaPlugin plugin, Runnable callback) {
//        Generator.getDBConnectionManager().connect(connection -> {
//            try {
//                PreparedStatement stmt = connection.prepareStatement("SELECT uuid, xp, multiplier, generator_slots FROM user;");
//                ResultSet resultSet = stmt.executeQuery();
//                while (resultSet.next()) {
//                    UUID uuid = UUID.fromString(resultSet.getString(1));
//                    double xp = resultSet.getDouble(2);
//                    double multiplier = resultSet.getDouble(3);
//                    long generatorSlots = resultSet.getLong(4);
//                    users.put(uuid, new User(uuid, multiplier, xp, generatorSlots));
//                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            System.out.println(plugin.getName() + " Loaded " + users.size() + " users");
//            callback.run();
//        });
//
//    }
//
    public Team getTeam(String name) {
        return teams.get(name);
    }

    public void createTeam(Team team) {
        if(teams.containsKey(team.getName())) {
            throw new IllegalArgumentException("Team already exists!");
        }
        Generator.getInstance().getDBConnectionManager().connect((connection) -> {
            try {
                // TODO: Create team in database
                // PreparedStatement stmt = connection.prepareStatement("INSERT INTO team (name) VALUES (?, ?)");
                teams.put(team.getName(), team);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void saveTeam(Team team) {
        teamsToSave.add(team);
    }

    public void removeTeam(Team team) {
        teams.remove(team.getName());
    }
    private void saveTeams(Set<Team> teamsToSave) {
        Set<Team> teams1 = new HashSet<>(teamsToSave);
        teams1.addAll(teams.values());
    }
}
