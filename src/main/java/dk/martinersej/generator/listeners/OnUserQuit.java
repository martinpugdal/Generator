package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnUserQuit implements Listener {

    @EventHandler
    public void onUserQuit(PlayerQuitEvent event) {
        GeneratorUser user = Generator.getUserManager().getUser(event.getPlayer().getUniqueId());
        Generator.getUserManager().removeActiveUser(user);
    }
}
