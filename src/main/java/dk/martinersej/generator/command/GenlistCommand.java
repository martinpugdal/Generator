package dk.martinersej.generator.command;

import dk.martinersej.generator.generator.guis.GenlistGUI;
import dk.martinersej.generator.utils.command.Command;
import dk.martinersej.generator.utils.gui.PaginatedGui;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GenlistCommand extends Command implements CommandExecutor {
    public GenlistCommand(JavaPlugin plugin) {
        super(plugin);
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
