package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnUserQuit implements Listener {

    @EventHandler
    public void onUserJoin(PlayerQuitEvent event) {
        GeneratorUser user = Generator.getGeneratorManager().getUser(event.getPlayer().getUniqueId());
        if (user == null) {
            user = new GeneratorUser(event.getPlayer().getUniqueId());
            Generator.getGeneratorManager();
            Generator.getGeneratorManager().addUser(user);
        }
    }
}
