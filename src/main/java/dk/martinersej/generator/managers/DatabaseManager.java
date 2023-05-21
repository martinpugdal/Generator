package dk.martinersej.generator.managers;

import dk.martinersej.generator.Generator;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DatabaseManager {

    private final String connectionString;
    private final Lock lock = new ReentrantLock(true);
    private Connection connection;

    public DatabaseManager(JavaPlugin plugin) {
        this.connectionString = String.format("jdbc:sqlite:%s%s%s.db", plugin.getDataFolder(), File.separator, plugin.getDescription().getName());
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createTables(JavaPlugin plugin, Runnable callback) {
        String sql = new BufferedReader(new InputStreamReader(plugin.getResource("init.sql"), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));

        this.connect((connection) -> {
            try {
                this.connection.createStatement().executeUpdate(sql);
                callback.run();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void connect(Consumer<Connection> callback) {
        asyncFuture(() -> {
            if (connection == null) {
                try {
                    connection = DriverManager.getConnection(connectionString);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            callback.accept(connection);
        });
    }

    public void close() throws SQLException {
        connection.close();
    }

    private CompletableFuture<Void> asyncFuture(Runnable runnable) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskAsynchronously(Generator.getInstance(), () -> {
            lock.lock();
            try {
                runnable.run();
            } finally {
                lock.unlock();
            }
            future.complete(null);
        });
        return future;
    }
}
