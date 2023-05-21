package dk.martinersej.generator.managers;

import dk.martinersej.generator.Generator;
import dk.martinersej.generator.generator.GeneratorElement;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;

public class GeneratorManager {

    private final WeakHashMap<GeneratorElement, Location> activeGenerators = new WeakHashMap<>();

    public GeneratorManager() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Generator.getInstance(), () -> {
            for(GeneratorElement generator : activeGenerators.keySet()) {
                generator.drop();
            }
        }, 20L*5, 20L*5);
    }

    public void loadAllGenerators() {
    }


    public void saveToDatabase(GeneratorElement element) {
        Generator.getDBConnectionManager().connect((connection) -> {
            try {
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO conveyorElements (loc_x, loc_y, loc_z, direction, world) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, element.getLocation().getBlockX());
                stmt.setInt(2, element.getLocation().getBlockY());
                stmt.setInt(3, element.getLocation().getBlockZ());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
