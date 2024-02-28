package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class OnUserJoin implements Listener {

    @EventHandler
    public void onUserJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        User user = Generator.getInstance().getUserManager().getUser(uuid);
        if (user == null) {
            user = new User(event.getPlayer().getUniqueId());
            Generator.getInstance().getUserManager().createUser(user);
        }
        Generator.getInstance().getUserManager().addActiveUser(user);
    }
}
