package dk.martinersej.generator.hook;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorUser;
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
    public String onRequest(OfflinePlayer player, String text) {

        if (player == null) {
            return "";
        }
        GeneratorUser generatorUser = Generator.getUserManager().getUser(player.getUniqueId());
        if (text.equalsIgnoreCase("generator_slots")) {
            return String.valueOf(generatorUser.getGeneratorSlots());
        } else if (text.equalsIgnoreCase("generator_amount")) {
            return String.valueOf(generatorUser.getGenerators().size());
        } else if (text.equalsIgnoreCase("has_chestcollector")) {
            return String.valueOf(generatorUser.getGeneratorChest() != null);
        } else if (text.equalsIgnoreCase("xp")) {
            return String.valueOf(generatorUser.getXp());
        } else if (text.equalsIgnoreCase("xp_formatted")) {
            return StringUtils.formatNumber(generatorUser.getXp());
        } else if (text.equalsIgnoreCase("multiplier")) {
            return String.valueOf(generatorUser.getMultiplier());
        } else if (text.equalsIgnoreCase("dyesprsec")) {
            double dropRatePrMinute = (double) 60 / Generator.GENERATOR_DROP_RATE;
            double dropRatePrSec = dropRatePrMinute / 60;
            double dropRate = dropRatePrSec * generatorUser.getGenerators().size();
            dropRate = Math.round(dropRate * 100.0) / 100.0;
            return String.valueOf(dropRate);
        }

        return null;
    }
}
