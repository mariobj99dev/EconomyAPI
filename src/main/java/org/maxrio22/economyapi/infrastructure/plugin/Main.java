package org.maxrio22.economyapi.infrastructure.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.maxrio22.economyapi.infrastructure.config.AppConfig;
import org.maxrio22.economyapi.infrastructure.database.DatabaseConnection;

public final class Main extends JavaPlugin {
    private AppConfig appConfig;
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("🚀 Activando EconomyAPI...");

        this.appConfig = new AppConfig();

        CommandRegistry.registerCommands(this, appConfig);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("🔌 Desactivando EconomyAPI...");
        DatabaseConnection.getInstance().close();
    }
}

