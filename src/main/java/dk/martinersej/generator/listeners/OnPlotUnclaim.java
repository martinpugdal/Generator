package dk.martinersej.generator.listeners;

import com.intellectualcrafters.plot.object.Plot;
import com.plotsquared.bukkit.events.PlotDeleteEvent;
import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Set;

public class OnPlotUnclaim implements Listener {

    @EventHandler
    public void onPlotUnclaim(PlotDeleteEvent event) {
        for (Plot plot : event.getPlot().getConnectedPlots()) {
            System.out.println(plot.getCorners().clone().length);
//            Set<GeneratorElement> elementSet = Generator.getGeneratorManager().getElementsBetweenXandZ(plot.getCorners().clone());
//            Generator.getGeneratorManager().removeAll(elementSet);
        }
    }
}
