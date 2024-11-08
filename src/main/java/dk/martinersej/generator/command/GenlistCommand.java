package dk.martinersej.generator.command;

import dk.martinersej.generator.guis.generator.GenlistGUI;
import dk.martinersej.generator.utils.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GenlistCommand extends Command implements CommandExecutor {
    public GenlistCommand(String name) {
        super(name);
        Bukkit.getPluginCommand(name).setExecutor(this);
    }

    @Deprecated
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        new GenlistGUI().open((Player) commandSender);
        return true;
    }

}
