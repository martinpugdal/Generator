package dk.martinersej.generator.hooks;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.User;
import dk.martinersej.generator.utils.StringUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    private final JavaPlugin plugin;

    public PlaceholderAPIHook(JavaPlugin plugin) {
        this.plugin = plugin;
        if (plugin.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            register();
        }
    }

    @Override
    public @NotNull String getIdentifier() {
        return Generator.getInstance().getDescription().getName();
    }

    @Override
    public @NotNull String getAuthor() {
        return Generator.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public @NotNull String getVersion() {
        return Generator.getInstance().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String text) {

        if (player == null) {
            return "";
        }
        User user = Generator.getInstance().getUserManager().getUser(player.getUniqueId());
        if (text.equalsIgnoreCase("generator_slots")) {
            return String.valueOf(user.getGeneratorSlots());
        } else if (text.equalsIgnoreCase("generator_amount")) {
            return String.valueOf(user.getGenerators().size());
        } else if (text.equalsIgnoreCase("has_chestcollector")) {
            return String.valueOf(user.getGeneratorChest() != null);
        } else if (text.equalsIgnoreCase("xp")) {
            return String.valueOf(user.getXp());
        } else if (text.equalsIgnoreCase("xp_formatted")) {
            return StringUtils.formatNumber(user.getXp());
        } else if (text.equalsIgnoreCase("multiplier")) {
            return String.valueOf(user.getMultiplier());
        } else if (text.equalsIgnoreCase("dyesprsec")) {
            double dropRatePrMinute = (double) 60 / Generator.GENERATOR_DROP_RATE;
            double dropRatePrSec = dropRatePrMinute / 60;
            double dropRate = dropRatePrSec * user.getGenerators().size();
            dropRate = Math.floor(dropRate * 100.0) / 100.0;
            return String.valueOf(dropRate);
        } else if (text.equalsIgnoreCase("dyesprmin")) {
            double dropRatePrMinute = (double) 60 / Generator.GENERATOR_DROP_RATE;
            double dropRate = dropRatePrMinute * user.getGenerators().size();
            dropRate = Math.floor(dropRate * 100.0) / 100.0;
            return String.valueOf(dropRate);
        }

        return null;
    }
}
