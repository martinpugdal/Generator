package dk.martinersej.generator.command;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.command.subteam.TeamCreateCommand;
import dk.martinersej.generator.generator.Team;
import dk.martinersej.generator.generator.User;
import dk.martinersej.generator.guis.team.TeamGUI;
import dk.martinersej.generator.utils.command.Command;
import dk.martinersej.generator.utils.command.CommandResult;
import dk.martinersej.generator.utils.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class TeamCommand extends Command implements CommandExecutor, TabCompleter {
    public TeamCommand(String name, JavaPlugin plugin) {
        super(name);
        Bukkit.getPluginCommand(name).setExecutor(this);

        addSubCommand(new TeamCreateCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        CommandResult result = super.execute(commandSender, strings);
        switch (result.getResult()) {
            case WRONG_USAGE:
                commandSender.sendMessage(String.format("Korrekt brug: %s", result.getSubCommand().getUsage(s)));
                return true;
            case NO_PERMISSION:
                commandSender.sendMessage("§cDu har ikke adgang til dette");
                return true;
            case NO_SUB_COMMAND_FOUND:
                if (!(commandSender instanceof Player) && strings.length == 0) {
                    sendNoSubCommandFoundMessage(commandSender);
                    return true;
                }
                if (commandSender instanceof Player) {
                    Player player = (Player) commandSender;
                    User user = Generator.getInstance().getUserManager().getUser(player);
                    Team team = user.getTeam();

                    if (team == null) {
                        sendNoSubCommandFoundMessage(commandSender);
                    } else {
                        Bukkit.broadcastMessage("Team: " + team.getName());
                        TeamGUI teamGUI = new TeamGUI(team);
                        teamGUI.open(player);
                        //TODO: open team menu
                    }
                    return true;
                }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return getAllowedSubCommands(commandSender, command, s, strings);
    }

    private void sendNoSubCommandFoundMessage(CommandSender commandSender) {
        commandSender.sendMessage("Det er ikke en gyldig subkommando");
        commandSender.sendMessage("Gyldige subkommandoer:");
        for (SubCommand cmd : super.getSubCommands()) {
            commandSender.sendMessage(" - " + cmd.getUsage(getName()) + " - " + cmd.getDescription());
        }
    }
}
