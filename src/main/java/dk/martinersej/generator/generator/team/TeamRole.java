package dk.martinersej.generator.generator.team;

public enum TeamRole {

    LEADER,
    CO_LEADER,
    ELDER,
    MEMBER;

    public static TeamRole getRole(String role) {
        for (TeamRole teamRole : values()) {
            if (teamRole.name().equalsIgnoreCase(role)) {
                return teamRole;
            }
        }
        return null;
    }

    public int getPriority() {
        switch (this) {
            case LEADER:
                return 3;
            case CO_LEADER:
                return 2;
            case ELDER:
                return 1;
            default:
                return 0;
        }
    }
}
