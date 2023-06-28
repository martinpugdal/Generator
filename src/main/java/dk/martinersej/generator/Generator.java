package dk.martinersej.generator;

import dk.martinersej.generator.command.GeneratorCommand;
import dk.martinersej.generator.command.GenlistCommand;
import dk.martinersej.generator.hook.PlaceholderAPIHook;
import dk.martinersej.generator.listeners.*;
import dk.martinersej.generator.managers.DatabaseManager;
import dk.martinersej.generator.managers.GeneratorManager;
import dk.martinersej.generator.managers.UserManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Generator extends JavaPlugin {

    public static final long GENERATOR_DROP_RATE = 20 * 6; // 6 seconds
    private static Generator instance;
    private static DatabaseManager databaseManager;
    private static GeneratorManager generatorManager;
    private static UserManager userManager;


    public static DatabaseManager getDBConnectionManager() {
        return databaseManager;
    }

    public static Generator getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Generator is not loaded!");
        }
        return instance;
    }

    public static GeneratorManager getGeneratorManager() {
        return generatorManager;
    }

    public static UserManager getUserManager() {
        return userManager;
    }

    @Override
    public void onLoad() {
        instance = this;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        databaseManager = new DatabaseManager(this);
        generatorManager = new GeneratorManager(this, GENERATOR_DROP_RATE);
        userManager = new UserManager(this, 20 * 60 * 5); // 15 minutes

        databaseManager.createTables(this,
                () -> userManager.loadAll(this, () -> generatorManager.loadAll(this))
        );

        registerListeners();
        registerCommands();

        new PlaceholderAPIHook(this);
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new GlobalListeners(), this);

        this.getServer().getPluginManager().registerEvents(new OnGeneratorPlace(), this);
        this.getServer().getPluginManager().registerEvents(new OnGeneratorBreak(), this);
        this.getServer().getPluginManager().registerEvents(new OnGeneratorChestRightClick(), this);
        this.getServer().getPluginManager().registerEvents(new OnUserJoin(), this);
        this.getServer().getPluginManager().registerEvents(new OnUserQuit(), this);
        this.getServer().getPluginManager().registerEvents(new OnPlotUnclaim(), this);
        this.getServer().getPluginManager().registerEvents(new OnGeneratorUpgrade(), this);
    }

    private void registerCommands() {
        this.getCommand("generator").setExecutor(new GeneratorCommand(this));
        this.getCommand("genlist").setExecutor(new GenlistCommand(this));
    }

    @Override
    public void onDisable() {
        try {
            databaseManager.close();
        } catch (SQLException ignored) {
        }
    }
}
