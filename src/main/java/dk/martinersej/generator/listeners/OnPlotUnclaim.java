package dk.martinersej.generator.listeners;

import com.intellectualcrafters.plot.object.Plot;
import com.plotsquared.bukkit.events.PlotDeleteEvent;
import com.plotsquared.bukkit.events.PlotUnlinkEvent;
import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlotUnclaim implements Listener {

    @EventHandler
    public void OnPlotUnclaim(PlotDeleteEvent event) {

        for (Plot plot : event.getPlot().getConnectedPlots()) {
            plot.getAllCorners();
            System.out.println(plot.toString());
            System.out.println(plot.getAllCorners());
        }
    }
}
