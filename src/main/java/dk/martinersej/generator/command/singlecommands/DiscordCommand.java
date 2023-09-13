package dk.martinersej.generator.command.singlecommands;

import dk.martinersej.generator.utils.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordCommand extends Command implements CommandExecutor {
    public DiscordCommand(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        commandSender.sendMessage("§6Discord: §9https://discord.gg/CuWMK8FJxS");
        return true;
    }

}
