package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.GeneratorItem;
import dk.martinersej.generator.generator.User;
import dk.martinersej.generator.generator.block.GeneratorBlockItem;
import dk.martinersej.generator.generator.chest.GeneratorChestItem;
import dk.martinersej.generator.utils.GeneratorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class OnGeneratorPlace implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onGeneratorPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack heldItem = player.getItemInHand();
        if (heldItem == null) {
            return;
        }

        GeneratorItem<?> generatorItem = null;
        try {
            generatorItem = GeneratorUtils.fromItemStack(heldItem);
        } catch (Exception ignored) {
        }
        if (generatorItem == null) {
            return;
        }

        if (generatorItem instanceof GeneratorChestItem) {
            if (Generator.getInstance().getUserManager().getUser(player.getUniqueId()).getGeneratorChest() != null) {
                player.sendMessage("§c§oDu har allerede en generator chest");
                event.setCancelled(true);
                return;
            }
        } else if (generatorItem instanceof GeneratorBlockItem) {
            User user = Generator.getInstance().getUserManager().getUser(player.getUniqueId());
            if (user.getGenerators().size() >= user.getGeneratorSlots()) {
                player.sendMessage("§c§oDu kan ikke placere flere generatorer");
                event.setCancelled(true);
                return;
            }
        }
        GeneratorElement element = generatorItem.place(event.getBlockPlaced().getLocation(), player.getUniqueId());
        if (element == null) {
            return;
        }
        Generator.getInstance().getGeneratorManager().addElement(element);
    }
}
