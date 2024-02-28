package dk.martinersej.generator.generator;

import dk.martinersej.generator.generator.team.TeamRole;
import dk.martinersej.generator.generator.team.TeamUser;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String name;
    List<TeamUser> users = new ArrayList<>();

    public Team(String teamName, TeamUser user) {
        this.name = teamName;
        this.users.add(user);
    }

    private void addToSaveQueue() {

    }

    public void addUser(User user) {
        this.users.add(new TeamUser(this, user, TeamRole.MEMBER));
    }

    public void removeUser(TeamUser teamUser) {
        this.users.remove(teamUser);
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
}
