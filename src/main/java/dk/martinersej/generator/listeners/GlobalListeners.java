package dk.martinersej.generator.listeners;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.Team;
import dk.martinersej.generator.generator.User;
import dk.martinersej.generator.hooks.VaultHook;
import dk.martinersej.generator.utils.LabymodUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class GlobalListeners implements Listener {


    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        User user = Generator.getInstance().getUserManager().getUser(player);
        Team team = user.getTeam();
        String teamFormat = "";
        if (team != null) {
            StringBuilder teamRoleFormat = new StringBuilder();
            int teamRole = user.getTeamUser().getRole().getPriority();
            for (int i = 0; i < teamRole; i++) {
                teamRoleFormat.append("§e★");
            }
            teamFormat = "§8[" + teamRoleFormat + "§a" + team.getName() + "§8]";
        }
        String message = event.getMessage().replace("%", "%%");

        String userPrefix = VaultHook.getChat().getPlayerPrefix(player);
        userPrefix = userPrefix == null ? "" : ChatColor.translateAlternateColorCodes('&', userPrefix);

        String messageFormat = teamFormat + userPrefix + "§r " + player.getName() + " §8» §f" + message;

        event.setFormat(messageFormat);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        LabymodUtil.sendServerBanner(player, "https://imgur.com/c4pks7e.png");

        if (Bukkit.getOnlinePlayers().size() > 20) event.setJoinMessage(null);
        else
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "§8[§a+§8] " + VaultHook.getChat().getPlayerPrefix(player) + "&r " + player.getName()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Bukkit.getOnlinePlayers().size() > 20) event.setQuitMessage(null);
        else
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "§8[§c-§8] " + VaultHook.getChat().getPlayerPrefix(player) + "&r " + player.getName()));
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.getWorld().setTime(0);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onThunderChange(ThunderChangeEvent event) {
        if (event.toThunderState()) {
            event.setCancelled(true);
        }
    }
}
