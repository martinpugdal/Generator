package dk.martinersej.generator.generator;

import dk.martinersej.generator.generator.block.GeneratorBlock;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import dk.martinersej.generator.generator.team.TeamRole;
import dk.martinersej.generator.generator.team.TeamUser;
import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Team {

    private String name;
    List<TeamUser> users = new ArrayList<>();

    public Team(String teamName, TeamUser user) {
        this.name = teamName;
        this.users.add(user);
        user.setTeam(this);
    }

    public void addUser(User user) {
        TeamUser teamUser = new TeamUser(user, TeamRole.MEMBER);
        teamUser.setTeam(this);
        this.users.add(teamUser);
    }

    public void addTeamUser(TeamUser teamUser) {
        this.users.add(teamUser);
        teamUser.setTeam(this);
    }

    public void addUser(User user, TeamRole role) {
        TeamUser teamUser = new TeamUser(user, role);
        teamUser.setTeam(this);
        this.users.add(teamUser);
    }

    public void removeUser(TeamUser teamUser) {
        this.users.remove(teamUser);
        teamUser.setTeam(null);
        teamUser.setRole(null);
    }

    public boolean containsTeamUser(TeamUser teamUser) {
        return this.users.contains(teamUser);
    }

    public boolean containsUser(User user) {
        for (TeamUser teamUser : this.users) {
            if (teamUser.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public List<TeamUser> getUsers() {
        return this.users;
    }

    public TeamUser getTeamUser(User user) {
        for (TeamUser teamUser : this.users) {
            if (teamUser.getUser().equals(user)) {
                return teamUser;
            }
        }
        return null;
    }

    public TeamUser getTeamUser(String username) {
        for (TeamUser teamUser : this.users) {
            if (teamUser.getUser().getPlayer().getName().equalsIgnoreCase(username)) {
                return teamUser;
            }
        }
        return null;
    }

    public List<GeneratorBlock> getGeneratorsInTeam() {
        List<GeneratorBlock> generators = new ArrayList<>();
        for (TeamUser teamUser : this.users) {
            generators.addAll(teamUser.getUser().getGenerators());
        }
        return generators;
    }

    public List<GeneratorChest> getChestsInTeam() {
        List<GeneratorChest> chests = new ArrayList<>();
        for (TeamUser teamUser : this.users) {
            if (teamUser.getUser().getGeneratorChest() == null) continue;
            chests.add(teamUser.getUser().getGeneratorChest());
        }
        return chests;
    }

    public void delete() {
        for (TeamUser teamUser : getUsers()) {
            teamUser.getUser().setTeamUser(null);
            teamUser.setTeam(null);
            teamUser.setRole(null);
            if (!teamUser.getRole().equals(TeamRole.LEADER) && teamUser.getUser().getPlayer().isOnline() && teamUser.getUser().getPlayer().getPlayer() != null) {
                teamUser.getUser().getPlayer().getPlayer().sendMessage("Dit team er blevet slettet");
            }
        }
        getUsers().clear();
    }
}
