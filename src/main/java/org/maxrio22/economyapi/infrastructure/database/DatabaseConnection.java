package org.maxrio22.economyapi.infrastructure.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final HikariDataSource dataSource;
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());

    private DatabaseConnection() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/economyapi?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
            config.setUsername("admin");
            config.setPassword("admin");

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setIdleTimeout(600000);
            config.setMaxLifetime(1800000);
            config.setKeepaliveTime(300000);
            config.setLeakDetectionThreshold(5000);

            this.dataSource = new HikariDataSource(config);
            LOGGER.info("✅ Pool de conexiones inicializado correctamente.");
        } catch (Exception e) {
            LOGGER.severe("❌ ERROR al inicializar la conexión a la base de datos: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("❌ ERROR al inicializar la conexión a la base de datos", e);
        }
    }


    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) { // CORREGIDO
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close() {
        try {
            if (!dataSource.isClosed()) {
                dataSource.close();
                LOGGER.info("🔌 Conexión a la base de datos cerrada.");
            }
        } catch (Exception e) {
            LOGGER.severe("❌ Error al cerrar la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
