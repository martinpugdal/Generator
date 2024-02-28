package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.block.GeneratorType;
import dk.martinersej.generator.generator.block.GeneratorBlock;
import dk.martinersej.generator.hooks.VaultHook;
import dk.martinersej.generator.utils.GeneratorUtils;
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

        try {
            if (GeneratorUtils.fromItemStack(player.getItemInHand()) != null) {
                return;
            }
        } catch (Exception ignored) {
        }

        GeneratorBlock element = Generator.getInstance().getGeneratorManager().getGenerator(event.getClickedBlock().getLocation());

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
                player.sendMessage("§aDu har opgraderet din generator til " + element.getGeneratorType().getDisplayName() + "!");
            } else {
                player.sendMessage("§cDu har ikke råd til at opgradere denne generator!" + " §8(§c" + (price - VaultHook.getEconomy().getBalance(player)) + "§8)");
            }
        } else {
            player.sendMessage("§cDu kan ikke opgradere denne generator!");
        }
    }
}
