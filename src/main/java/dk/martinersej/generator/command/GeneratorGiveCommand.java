package dk.martinersej.generator.command;

import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.generator.block.GeneratorBlockItem;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import dk.martinersej.generator.generator.chest.GeneratorChestItem;
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
                "<item> <amount>",
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
        sender.sendMessage(args);
        if (args.length < 1) {
            return new CommandResult(this, Result.WRONG_USAGE);
        }
        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("generator")) {
            GeneratorType generatorType = GeneratorType.values()[random.nextInt(GeneratorType.values().length)];
            ItemStack itemStack = generatorType.getItemStack();
            itemStack.setAmount(Integer.parseInt(args[1] == null ? "1" : args[1]));
            player.getInventory().addItem(itemStack);
        } else if (args[0].equalsIgnoreCase("chest")) {
            ItemStack itemStack = new GeneratorChestItem().toItemStack();
            itemStack.setAmount(Integer.parseInt(args[1] == null ? "1" : args[1]));
            player.getInventory().addItem(itemStack);
        }

        return new CommandResult(this, Result.SUCCESS);
    }
}
