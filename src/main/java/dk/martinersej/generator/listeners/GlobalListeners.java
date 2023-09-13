package dk.martinersej.generator.listeners;

import dk.martinersej.generator.hooks.VaultHook;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class GlobalListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Bukkit.getOnlinePlayers().size() > 20) return;
        System.out.println(VaultHook.getChat().getPlayerPrefix(player));
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "§8[§a+§8] " + VaultHook.getChat().getPlayerPrefix(player) + "&r " + player.getName()));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Bukkit.getOnlinePlayers().size() > 20) return;
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "§8[§c-§8] " + VaultHook.getChat().getPlayerPrefix(player) + "&r " + player.getName()));
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
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
