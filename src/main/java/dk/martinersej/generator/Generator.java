package dk.martinersej.generator;

import dk.martinersej.generator.command.GeneratorCommand;
import dk.martinersej.generator.command.GenlistCommand;
import dk.martinersej.generator.command.TeamCommand;
import dk.martinersej.generator.command.singlecommands.DiscordCommand;
import dk.martinersej.generator.hooks.PlaceholderAPIHook;
import dk.martinersej.generator.listeners.*;
import dk.martinersej.generator.managers.DatabaseManager;
import dk.martinersej.generator.managers.GeneratorManager;
import dk.martinersej.generator.managers.TeamManager;
import dk.martinersej.generator.managers.UserManager;
import dk.martinersej.generator.utils.gui.OnGuiClick;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Generator extends JavaPlugin {

    public static final long GENERATOR_DROP_RATE = 20 * 8; // 8 seconds
    private static Generator instance;
    private DatabaseManager databaseManager;
    private GeneratorManager generatorManager;
    private UserManager userManager;
    private TeamManager teamManager;


    public DatabaseManager getDBConnectionManager() {
        return databaseManager;
    }

    public static Generator getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Generator is not loaded!");
        }
        return instance;
    }

    public GeneratorManager getGeneratorManager() {
        return generatorManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public TeamManager getTeamManager() {
        return teamManager;
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
        teamManager = new TeamManager(this, 20 * 60 * 5); // 15 minutes

        databaseManager.createTables(this,
                () -> userManager.loadAll(() ->  {
                    generatorManager.loadAll();
                    teamManager.loadAll();
                })
        );

        registerListeners();
        registerCommands();

        runHooks();
    }

    private void runHooks() {
        new PlaceholderAPIHook();
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new GlobalListeners(), this);

        this.getServer().getPluginManager().registerEvents(new OnGuiClick(), this);

        this.getServer().getPluginManager().registerEvents(new OnGeneratorPlace(), this);
        this.getServer().getPluginManager().registerEvents(new OnGeneratorBreak(), this);
        this.getServer().getPluginManager().registerEvents(new OnGeneratorChestRightClick(), this);
        this.getServer().getPluginManager().registerEvents(new OnUserJoin(), this);
        this.getServer().getPluginManager().registerEvents(new OnUserQuit(), this);
        this.getServer().getPluginManager().registerEvents(new OnPlotUnclaim(), this);
        this.getServer().getPluginManager().registerEvents(new OnGeneratorUpgrade(), this);
    }

    private void registerCommands() {
        this.getCommand("discord").setExecutor(new DiscordCommand(this));

        this.getCommand("generator").setExecutor(new GeneratorCommand(this));
        this.getCommand("genlist").setExecutor(new GenlistCommand(this));

        this.getCommand("team").setExecutor(new TeamCommand(this));
    }

    @Override
    public void onDisable() {
        userManager.saveAllSync();
        teamManager.saveAllSync();
        try {
            databaseManager.close();
        } catch (SQLException ignored) {
        }
    }
}
