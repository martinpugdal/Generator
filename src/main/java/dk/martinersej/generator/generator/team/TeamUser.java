package dk.martinersej.generator.generator.team;

import dk.martinersej.generator.generator.Team;
import dk.martinersej.generator.generator.User;

public class TeamUser {

    private Team team;
    private final User user;
    private TeamRole role;

    public TeamUser(User user, TeamRole role) {
        user.setTeamUser(this);
        this.user = user;
        this.role = role;
    }

    public void setTeam(Team team) {
        this.team = team;
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

    public void setRole(TeamRole role) {
        this.role = role;
    }
}
