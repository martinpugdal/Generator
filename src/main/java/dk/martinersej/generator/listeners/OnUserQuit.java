package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnUserQuit implements Listener {

    @EventHandler
    public void onUserQuit(PlayerQuitEvent event) {
        User user = Generator.getInstance().getUserManager().getUser(event.getPlayer().getUniqueId());
        Generator.getInstance().getUserManager().removeActiveUser(user);
    }
}
