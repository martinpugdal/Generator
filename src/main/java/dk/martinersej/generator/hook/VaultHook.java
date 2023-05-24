package dk.martinersej.generator.hook;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.UUID;

public class VaultHook {

    private static Economy econ;

    static {
        setupEconomy();
    }


    private static void setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        econ = rsp.getProvider();
    }

    public static Economy getEconomy() throws IllegalStateException {
        if(econ == null) {
            throw new IllegalStateException("Economy is not setup!");
        }
        return econ;
    }

    public static void addBalance(OfflinePlayer player, double amount) throws IllegalStateException {
        getEconomy().depositPlayer(player, amount);
    }

    public static void addBalance(UUID owner, double amount) throws IllegalStateException {
        getEconomy().depositPlayer(Bukkit.getOfflinePlayer(owner), amount);
    }
}
