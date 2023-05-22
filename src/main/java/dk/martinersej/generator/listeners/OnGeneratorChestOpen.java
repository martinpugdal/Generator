package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnGeneratorChestOpen implements Listener {

    @EventHandler
    public void onGeneratorChestOpen(PlayerInteractEvent event) {
        if (!event.hasBlock() || !(event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Location location = block.getLocation();

        GeneratorElement element = Generator.getGeneratorManager().getCollectorChest(location);
        if(element == null) {
            return;
        }
        if (element instanceof GeneratorChest) {
            event.setCancelled(true);
            ((GeneratorChest) element).openGUI(player);
        }
    }
}
