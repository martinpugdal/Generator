package dk.martinersej.generator.hooks;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

public class PlotHook {

    private static PlotAPI plotAPI;

    static {
        setupPlotAPI();
    }

    private static void setupPlotAPI() {
        if (Bukkit.getServer().getPluginManager().getPlugin("PlotSquared") == null) {
            return;
        }
        plotAPI = new PlotAPI();
    }

    public static boolean canBuildAt(Location loc, OfflinePlayer player) {
        Plot plot = plotAPI.getPlot(loc);
        if (plot == null) {
            return false;
        }
        return plot.isAdded(player.getUniqueId());
    }

    public static Plot getPlotAt(Location loc) {
        return plotAPI.getPlot(loc);
    }

    public static PlotAPI getPlotAPI() {
        return plotAPI;
    }
}
