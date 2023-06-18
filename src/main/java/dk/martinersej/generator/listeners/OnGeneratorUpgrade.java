package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.GeneratorItem;
import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.generator.block.GeneratorBlock;
import dk.martinersej.generator.hook.VaultHook;
import dk.martinersej.generator.utils.GeneratorUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnGeneratorUpgrade implements Listener {

    @EventHandler
    public void onGeneratorUpgrade(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        Player player = event.getPlayer();

        if (player.isSneaking()) {
            return;
        }
        GeneratorItem generatorItem = null;
        try {
            generatorItem = GeneratorUtils.fromItemStack(player.getItemInHand());
        } catch (Exception ignored) {}
        if (generatorItem != null) {
            return;
        }

        Block block = event.getClickedBlock();
        Location location = block.getLocation();
        GeneratorBlock element = Generator.getGeneratorManager().getGenerator(location);

        if (element == null) {
            return;
        }
        event.setCancelled(true);
        if (!element.getOwner().equals(player.getUniqueId())) {
            player.sendMessage("§cDu ejer ikke denne generator!");
            return;
        }

        if (element.isUpgradeable()) {
            long price = GeneratorType.UpgradePrice.valueOf(element.getGeneratorType().name()).getPrice();
            if (VaultHook.getEconomy().getBalance(player) >= price) {
                VaultHook.getEconomy().withdrawPlayer(player, price);
                element.upgrade();
                player.sendMessage("§aDu har opgraderet din generator!");
            } else {
                player.sendMessage("§cDu har ikke råd til at opgradere denne generator!");
            }
        } else {
            player.sendMessage("§cDu kan ikke opgradere denne generator!");
        }
    }
}
