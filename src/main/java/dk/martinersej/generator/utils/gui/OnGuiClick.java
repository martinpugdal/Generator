package dk.martinersej.generator.utils.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnGuiClick implements Listener {

    @EventHandler(priority = org.bukkit.event.EventPriority.HIGHEST) // This is to make sure the event is called before other plugins
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof BaseGui) {
            BaseGui gui = (BaseGui) event.getInventory().getHolder();
            if (gui.cooldownEnabled()) {
                gui.cooldownCheck(event);
            }
            gui.onInventoryClick(event);
        }
    }
}