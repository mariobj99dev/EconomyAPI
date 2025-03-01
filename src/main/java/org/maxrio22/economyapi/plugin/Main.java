package org.maxrio22.economyapi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.maxrio22.economyapi.infrastructure.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("🚀 Activando EconomyAPI...");

        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            Bukkit.getLogger().info("✅ Conexión a la base de datos establecida correctamente.");
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ ERROR: No se pudo conectar a la base de datos.");
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("🔌 Desactivando EconomyAPI...");
        DatabaseConnection.getInstance().close();
    }
}
