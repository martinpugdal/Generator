package dk.martinersej.generator.command.singlecommands;

import dk.martinersej.generator.guis.shop.ShopGUI;
import dk.martinersej.generator.utils.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand extends Command implements CommandExecutor {

    public ShopCommand(String name) {
        super(name);
        Bukkit.getPluginCommand(name).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (isPlayer(commandSender)) {
            Player player = (Player) commandSender;
            new ShopGUI().open(player);
        } else {
            commandSender.sendMessage("You must be a player to use this command");
        }
        return true;
    }
}
