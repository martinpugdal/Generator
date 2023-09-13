package dk.martinersej.generator.command.subgenerator;

import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.generator.chest.GeneratorChestItem;
import dk.martinersej.generator.generator.chest.GeneratorChestSellStickItem;
import dk.martinersej.generator.utils.command.CommandResult;
import dk.martinersej.generator.utils.command.Result;
import dk.martinersej.generator.utils.command.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class GeneratorGiveCommand extends SubCommand {

    private final Random random;

    public GeneratorGiveCommand(JavaPlugin plugin) {
        super(
                plugin,
                "Giver dig en generator",
                "give <generator|chest|sellstick|all>",
                "generator.give",
                "give"
        );
        this.random = new Random();
    }

    @Override
    public CommandResult execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Kun spillere må bruge denne kommando");
            return new CommandResult(this, Result.SUCCESS);
        }
        if (args.length < 1) {
            return new CommandResult(this, Result.WRONG_USAGE);
        }

        Player player = (Player) sender;
        if (args[0].equalsIgnoreCase("generator")) {
            GeneratorType generatorType = GeneratorType.values()[random.nextInt(GeneratorType.values().length)];
            if (args.length > 1) {
                generatorType = GeneratorType.values()[Integer.parseInt(args[1])];
            }
            ItemStack itemStack = generatorType.getItemStack();
            player.getInventory().addItem(itemStack);
        } else if (args[0].equalsIgnoreCase("chest")) {
            ItemStack itemStack = new GeneratorChestItem().toItemStack();
            player.getInventory().addItem(itemStack);
        } else if (args[0].equalsIgnoreCase("sellstick")) {
            ItemStack itemStack = new GeneratorChestSellStickItem().toItemStack();
            player.getInventory().addItem(itemStack);
        } else {
            return new CommandResult(this, Result.WRONG_USAGE);
        }

        return new CommandResult(this, Result.SUCCESS);
    }
}
