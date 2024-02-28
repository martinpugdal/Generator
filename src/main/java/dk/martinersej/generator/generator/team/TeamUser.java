package dk.martinersej.generator.generator.team;

import dk.martinersej.generator.generator.Team;
import dk.martinersej.generator.generator.User;

public class TeamUser {

    private final Team team;
    private final User user;
    private final TeamRole role;

    public TeamUser(Team team, User user, TeamRole role) {
        this.team = team;
        this.user = user;
        this.role = role;
    }

    public Team getTeam() {
        return this.team;
    }

    public User getUser() {
        return this.user;
    }

    public TeamRole getRole() {
        return this.role;
    }
}
