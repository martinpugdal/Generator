package dk.martinersej.generator.managers;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.Team;
import dk.martinersej.generator.generator.User;
import dk.martinersej.generator.generator.team.TeamRole;
import dk.martinersej.generator.generator.team.TeamUser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TeamManager {

    private final HashMap<String, Team> teams = new HashMap<>();
    private final Set<Team> teamsToSave = new HashSet<>();
    private final Set<String> disallowedNames = new HashSet<>();

    public TeamManager(JavaPlugin plugin, long saveInterval) {
        initDisallowedNames();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            saveTeams(teamsToSave);
        }, saveInterval, saveInterval);
    }

        public void loadAll() {
        Generator.getInstance().getDBConnectionManager().connect(connection -> {
            try {
                PreparedStatement stmt = connection.prepareStatement(
                    "SELECT " +
                    "    t.name, " +
                    "    tu.user_uuid, " +
                    "    tu.team_role " +
                    "FROM " +
                    "    team_user AS tu " +
                    "LEFT JOIN " +
                    "    team AS t ON t.name = tu.team_name "
                );
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    String teamName = resultSet.getString("name");
                    String userUUID = resultSet.getString("user_uuid");
                    TeamRole teamRole = TeamRole.valueOf(resultSet.getString("team_role"));
                    User user = Generator.getInstance().getUserManager().getUser(UUID.fromString(userUUID));
                    Team team = teams.get(teamName);
                    if (team == null) {
                        team = new Team(teamName, new TeamUser(user, teamRole));
                        teams.put(teamName, team);
                    } else {
                        team.addUser(user, teamRole);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println(Bukkit.getName() + " Loaded " + teams.size() + " users");
        });

    }

    public Team getTeam(String name) {
        return teams.get(name);
    }

    public void createTeam(String teamName, OfflinePlayer player) {
        User user = Generator.getInstance().getUserManager().getUser(player.getUniqueId());
        TeamUser teamUser = new TeamUser(user, TeamRole.LEADER);
        createTeam(new Team(teamName, teamUser));
    }

    public void createTeam(Team team) {
        if (teams.containsKey(team.getName())) {
            throw new IllegalArgumentException("Team already exists!");
        }
        Generator.getInstance().getDBConnectionManager().connect((connection) -> {
            try {
                connection.setAutoCommit(false);

                PreparedStatement stmt = connection.prepareStatement("INSERT INTO team (name) VALUES (?)");
                stmt.setString(1, team.getName());
                teams.put(team.getName(), team);

                PreparedStatement stmt2 = connection.prepareStatement("INSERT INTO team_user (team_name, user_uuid, team_role) VALUES (?, ?, ?)");
                for (TeamUser teamUser : team.getUsers()) {
                    stmt2.setString(1, team.getName());
                    stmt2.setString(2, teamUser.getUser().getUUID().toString());
                    stmt2.setString(3, teamUser.getRole().name());
                    stmt2.addBatch();
                }

                stmt.executeUpdate();
                stmt2.executeBatch();

                connection.commit();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void saveTeam(Team team) {
        teamsToSave.add(team);
    }

    public void deleteTeam(Team team) {
        teamsToSave.remove(team);
        team.delete();
        Generator.getInstance().getDBConnectionManager().connect((connection) -> {
            try {
                PreparedStatement stmt = connection.prepareStatement("DELETE FROM team WHERE name = ?");
                stmt.setString(1, team.getName());
                stmt.executeUpdate();
                teams.remove(team.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void saveTeams(Set<Team> teamsToSave) {
        Set<Team> teams1 = new HashSet<>(teamsToSave);
        teams1.addAll(teams.values());
        Generator.getInstance().getDBConnectionManager().connect((connection) -> {
            try {
                connection.setAutoCommit(false);
                PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE team_user "+
                        "SET "+
                        "team_name = ?, " +
                        "team_role = ? " +
                        "WHERE " +
                        "user_uuid = ?"
                );
                for (Team team : teams1) {
                    for (TeamUser teamUser : team.getUsers()) {
                        stmt.setString(1, team.getName());
                        stmt.setString(2, teamUser.getRole().name());
                        stmt.setString(3, teamUser.getUser().getUUID().toString());
                        stmt.addBatch();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public boolean teamNameExists(String teamName) {
        return teams.containsKey(teamName);
    }

    public boolean isNameValid(String teamName) {
        return teamName.length() >= 3 && teamName.length() <= 16;
    }

    private void initDisallowedNames() {
        disallowedNames.clear();
        disallowedNames.add("nigger");
        disallowedNames.add("perker");
    }

    public boolean isNameAllowed(String teamName) {
        return teamName.matches("^[a-zA-Z0-9_]*$") && !disallowedNames.contains(teamName);
    }

    public void saveAllSync() {
    }
}
