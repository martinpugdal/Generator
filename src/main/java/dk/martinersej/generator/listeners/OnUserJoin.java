package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class OnUserJoin implements Listener {

    @EventHandler
    public void onUserJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        GeneratorUser user = Generator.getUserManager().getUser(uuid);
        if (user == null) {
            user = new GeneratorUser(event.getPlayer().getUniqueId());
            Generator.getUserManager().createUser(user);
        }
        Generator.getUserManager().addActiveUser(user);
    }
}
