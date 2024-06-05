package dk.martinersej.generator.command.subteam;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.Team;
import dk.martinersej.generator.generator.team.TeamRole;
import dk.martinersej.generator.generator.team.TeamUser;
import dk.martinersej.generator.managers.TeamManager;
import dk.martinersej.generator.managers.UserManager;
import dk.martinersej.generator.utils.command.CommandResult;
import dk.martinersej.generator.utils.command.Result;
import dk.martinersej.generator.utils.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TeamDeleteCommand extends SubCommand {

    public TeamDeleteCommand(JavaPlugin plugin) {
        super(
            plugin,
            "Slet dit team",
            "",
            "",
            "slet", "delete"
        );
    }

    @Override
    public CommandResult execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            return Result.getCommandResult(this, Result.WRONG_USAGE);
        }
        Player player = (Player) sender;
        UserManager userManager = Generator.getInstance().getUserManager();
        Team team = userManager.getUser(player.getUniqueId()).getTeam();
        if (team == null) {
            sender.sendMessage("§cDu er ikke i et team");
            return Result.getCommandResult(this, Result.SUCCESS);
        }
        TeamUser teamUser = userManager.getUser(player.getUniqueId()).getTeamUser();
        if (!teamUser.getRole().equals(TeamRole.LEADER)) {
            sender.sendMessage("§cDu er ikke leder af dit team");
            return Result.getCommandResult(this, Result.SUCCESS);

        }
        TeamManager teamManager = Generator.getInstance().getTeamManager();
        teamManager.deleteTeam(team);
        sender.sendMessage("§aDit team er slettet");
        return Result.getCommandResult(this, Result.SUCCESS);
    }
}
