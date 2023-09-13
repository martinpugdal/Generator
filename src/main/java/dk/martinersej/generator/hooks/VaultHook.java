package dk.martinersej.generator.hooks;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.UUID;

public class VaultHook {

    private static Economy econ;
    private static Chat chat;

    static {
        setupEconomy();
    }


    private static void setupEconomy() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        // economy
        RegisteredServiceProvider<Economy> rspEconomy = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rspEconomy == null) {
            return;
        }
        econ = rspEconomy.getProvider();
        // chat
        RegisteredServiceProvider<Chat> rspChat = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
        if (rspChat == null) {
            return;
        }
        chat = rspChat.getProvider();
    }

    public static Economy getEconomy() throws IllegalStateException {
        if (econ == null) {
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

    public static Chat getChat() {
        if (chat == null) {
            throw new IllegalStateException("Chat is not setup!");
        }
        return chat;
    }
}
