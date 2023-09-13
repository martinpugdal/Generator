package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import dk.martinersej.generator.generator.GeneratorType;
import dk.martinersej.generator.generator.chest.GeneratorChest;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.WeakHashMap;

public class OnGeneratorBreak implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onGeneratorBreak(PlayerInteractEvent event) {
        if (!(event.getAction() == Action.LEFT_CLICK_BLOCK)) {
            return;
        }

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Location location = block.getLocation();

        GeneratorElement element = Generator.getGeneratorManager().getElement(location);
        if (element == null) {
            return;
        }
        event.setCancelled(true);
        if (!player.isSneaking()) {
            return;
        }
        if (!element.getOwner().equals(player.getUniqueId())) {
            player.sendMessage("§c§oIkke din " + ((element instanceof GeneratorChest) ? "sell chest" : "generator") + "!");
            return;
        }
        if (element instanceof GeneratorChest) {
            GeneratorChest generatorChest = (GeneratorChest) element;
            WeakHashMap<GeneratorType, Integer> drops = generatorChest.getDrops();

            int MAX_ITEMS_IN_CHEST = 800;

            long total = 0;
            for (int i : drops.values()) {
                total += i;
                if (total > MAX_ITEMS_IN_CHEST) {
                    break;
                }
            }
            if (total > MAX_ITEMS_IN_CHEST) {
                player.sendMessage("§c§oDu kan ikke smadre en sell chest med mere end " + MAX_ITEMS_IN_CHEST + " items i!");
                return;
            }
            for (GeneratorType generatorType : drops.keySet()) {
                if (drops.containsKey(generatorType)) {
                    continue;
                }
                ItemStack itemStack = generatorType.getDrop();
                itemStack.setAmount(drops.get(generatorType));
                location.getWorld().dropItemNaturally(location, itemStack);
            }
        }
        location.getBlock().setType(Material.AIR);
        location.getWorld().dropItemNaturally(location, element.createItem().toItemStack());
        Generator.getGeneratorManager().removeElement(element);
    }
}
