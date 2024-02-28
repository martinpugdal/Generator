package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import dk.martinersej.generator.utils.GeneratorUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnGeneratorChestRightClick implements Listener {

    @EventHandler
    public void onGeneratorChestOpen(PlayerInteractEvent event) {
        if (!event.hasBlock() || !(event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Location location = block.getLocation();

        GeneratorChest element = Generator.getInstance().getGeneratorManager().getCollectorChest(location);
        if (element == null) {
            return;
        }
        event.setCancelled(true);
        if ("sellstick".equalsIgnoreCase(GeneratorUtils.getType(player.getItemInHand()))) {
            element.sellAll(player.getUniqueId());
        } else {
            element.openGUI(player);
        }
    }
}
