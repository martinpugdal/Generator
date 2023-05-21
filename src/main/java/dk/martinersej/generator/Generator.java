package dk.martinersej.generator;

import dk.martinersej.generator.managers.DatabaseManager;
import dk.martinersej.generator.managers.GeneratorManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Generator extends JavaPlugin {

    private static Generator instance;
    private DatabaseManager databaseManager;
    private GeneratorManager generatorManager;

    public static DatabaseManager getDBConnectionManager() {
        return getInstance().databaseManager;
    }

    public static Generator getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Generator is not loaded!");
        }
        return instance;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        databaseManager = new DatabaseManager(this);
        generatorManager = new GeneratorManager();

        databaseManager.createTables(this, () -> {
            generatorManager.loadAllGenerators();
        });
    }

    @Override
    public void onDisable() {
        try {
            databaseManager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
