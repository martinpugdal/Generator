package dk.martinersej.generator.listeners;

import com.intellectualcrafters.plot.object.Plot;
import com.plotsquared.bukkit.events.PlotDeleteEvent;
import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashSet;
import java.util.Set;

public class OnPlotUnclaim implements Listener {

    @Deprecated
    @EventHandler
    public void onPlotUnclaim(PlotDeleteEvent event) {
        Set<GeneratorElement> elements = new HashSet<>();
        for (Plot plot : event.getPlot().getConnectedPlots()) {
            elements.addAll(Generator.getGeneratorManager().getElementsBetweenXandZ(plot.getCorners().clone()));
        }
        Generator.getGeneratorManager().removeAll(elements);
    }
}
