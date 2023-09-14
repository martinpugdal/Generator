package dk.martinersej.generator.command;

import dk.martinersej.generator.utils.command.Command;
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
//        PaginatedGui genListGui = new PaginatedGui(5, "§6Generatorer");
//        genListGui.setDefaultClickAction(event -> event.setCancelled(true));
//
//        ItemStack itemStack = new ItemBuilder(Material.ARROW).toItemStack();
//
//        genListGui.getFiller().fillBorder(new GuiItem(itemStack));
//
//        for (GeneratorType generatorType : GeneratorType.values()) {
//            genListGui.addItem(new GuiItem(generatorType.getItemStack()));
//        }
//
//        GuiItem backItem = new GuiItem(new ItemBuilder(Material.ARROW).setName("Forrige side").toItemStack(), event -> {
//            genListGui.previous();
//        });
//        genListGui.setItem(3, 1, backItem);
//
//        GuiItem nextPageItem = new GuiItem(new ItemBuilder(Material.ARROW).setName("Næste side").toItemStack(), event -> {
//            genListGui.next();
//        });
//        genListGui.setItem(3, 9, nextPageItem);
//
//        genListGui.open((Player) commandSender);
        return true;
    }

}
