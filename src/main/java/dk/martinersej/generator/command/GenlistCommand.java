package dk.martinersej.generator.command;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.utils.ItemBuilder;
import dk.martinersej.generator.utils.command.Command;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GenlistCommand extends Command implements CommandExecutor {
    public GenlistCommand(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        PaginatedGui genlistGui = new PaginatedGui(5, "Generators");
        genlistGui.setDefaultClickAction(event -> event.setCancelled(true));

        ItemStack itemStack = new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short) 4).toItemStack();

        genlistGui.getFiller().fillBorder(new GuiItem(itemStack));

        for (GeneratorType generatorType : GeneratorType.values()) {
            genlistGui.addItem(new GuiItem(generatorType.getItemStack()));
        }

        GuiItem backItem = new GuiItem(new ItemBuilder(Material.BARRIER).setName("Forrige side").toItemStack(), event -> {
            genlistGui.previous();
        });
        genlistGui.setItem(3, 1, backItem);

        GuiItem nextPageItem = new GuiItem(new ItemBuilder(Material.ARROW).setName("Næste side").toItemStack(), event -> {
            genlistGui.next();
        });
        genlistGui.setItem(3, 9, nextPageItem);

        genlistGui.open((Player) commandSender);
        return true;
    }

}
