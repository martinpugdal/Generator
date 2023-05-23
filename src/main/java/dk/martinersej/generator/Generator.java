package dk.martinersej.generator;

import dk.martinersej.generator.command.GeneratorCommand;
import dk.martinersej.generator.listeners.OnGeneratorBreak;
import dk.martinersej.generator.listeners.OnGeneratorChestOpen;
import dk.martinersej.generator.listeners.OnGeneratorPlace;
import dk.martinersej.generator.managers.DatabaseManager;
import dk.martinersej.generator.managers.GeneratorManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Generator extends JavaPlugin {

    private static Generator instance;
    private static DatabaseManager databaseManager;
    private static GeneratorManager generatorManager;

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

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static GeneratorManager getGeneratorManager() {
        return generatorManager;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        databaseManager = new DatabaseManager(this);
        generatorManager = new GeneratorManager();

        databaseManager.createTables(this, () -> {
            generatorManager.loadAll();
        });

        registerListeners();
        registerCommands();
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new OnGeneratorPlace(), this);
        this.getServer().getPluginManager().registerEvents(new OnGeneratorBreak(), this);
        this.getServer().getPluginManager().registerEvents(new OnGeneratorChestOpen(), this);

    }

    private void registerCommands() {
        this.getCommand("generator").setExecutor(new GeneratorCommand(this));
    }

    @Override
    public void onDisable() {
        try {
            databaseManager.close();
        } catch (SQLException ignored) {
        }
    }
}
