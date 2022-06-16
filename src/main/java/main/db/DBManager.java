package main.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private final Connection connection;

    private static DBManager instance;

    private DBManager() {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("connect.props"))) {
            Properties properties = new Properties();
            properties.load(reader);
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
