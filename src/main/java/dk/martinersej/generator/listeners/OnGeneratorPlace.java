package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.GeneratorItem;
import dk.martinersej.generator.generator.chest.GeneratorChestItem;
import dk.martinersej.generator.utils.GeneratorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class OnGeneratorPlace implements Listener {

    @EventHandler
    public void onGeneratorPlace(BlockPlaceEvent event) {
        if (!event.canBuild()) {
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
            if (Generator.getGeneratorManager().getUser(player.getUniqueId()).getGeneratorChest() != null) {
                player.sendMessage("§c§oDu har allerede en generator chest");
                event.setCancelled(true);
                return;
            }
        }

        GeneratorElement element = generatorItem.place(event.getBlockPlaced().getLocation(), player.getUniqueId());
        if (element == null) {
            return;
        }
//        InventoryUtils.reduceItemInHand(player, 1);
        Generator.getGeneratorManager().addElement(element);
    }
}
