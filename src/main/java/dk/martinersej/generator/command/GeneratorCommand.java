package dk.martinersej.generator.command;

import dk.martinersej.generator.command.subgenerator.GeneratorGiveCommand;
import dk.martinersej.generator.utils.command.Command;
import dk.martinersej.generator.utils.command.CommandResult;
import dk.martinersej.generator.utils.command.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class GeneratorCommand extends Command implements CommandExecutor, TabCompleter {

    public GeneratorCommand(String name, JavaPlugin plugin) {
        super(name);
        Bukkit.getPluginCommand(name).setExecutor(this);

        addSubCommand(new GeneratorGiveCommand(plugin));
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
                commandSender.sendMessage("Det er ikke en gyldig subkommando");
                commandSender.sendMessage("Gyldige subkommandoer:");
                for (SubCommand cmd : super.getSubCommands()) {
                    commandSender.sendMessage("- " + cmd.getUsage(getName()) + " - " + cmd.getDescription());
                }
                return true;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return getAllowedSubCommands(commandSender, command, s, strings);
    }
}
