package dk.martinersej.generator.command.subteam;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.managers.TeamManager;
import dk.martinersej.generator.utils.command.CommandResult;
import dk.martinersej.generator.utils.command.Result;
import dk.martinersej.generator.utils.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class TeamCreateCommand extends SubCommand {

        public TeamCreateCommand(JavaPlugin plugin) {
            super(
                plugin,
                "Opretter et nyt team",
                "<teamnavn>",
                "",
                "opret"
            );
        }

    @Override
    public CommandResult execute(CommandSender sender, String[] args) {
        if (args.length != 1 || !(sender instanceof Player)) {
            return Result.getCommandResult(this, Result.WRONG_USAGE);
        }

        String teamName = args[0];
        TeamManager teamManager = Generator.getInstance().getTeamManager();
        boolean nameTaked = teamManager.teamNameExists(teamName);
        boolean nameValid = teamManager.isNameValid(teamName);
        boolean nameAllowed = teamManager.isNameAllowed(teamName);

        if (nameTaked) {
            sender.sendMessage("§cTeamnavnet er allerede taget");
            return Result.getCommandResult(this, Result.SUCCESS);
        }
        if (!nameValid) {
            sender.sendMessage("§cTeamnavnet er ikke gyldigt");
            return Result.getCommandResult(this, Result.SUCCESS);
        }
        if (!nameAllowed) {
            sender.sendMessage("§cTeamnavnet er ikke tilladt");
            return Result.getCommandResult(this, Result.SUCCESS);
        }
        Player player = (Player) sender;
        teamManager.createTeam(teamName, player);
        sender.sendMessage("§aTeamet er oprettet");
        return Result.getCommandResult(this, Result.SUCCESS);
    }
}
