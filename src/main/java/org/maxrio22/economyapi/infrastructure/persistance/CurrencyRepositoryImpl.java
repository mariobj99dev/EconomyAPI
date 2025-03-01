package org.maxrio22.economyapi.infrastructure.persistance;

import org.bukkit.Bukkit;
import org.maxrio22.economyapi.domain.model.Currency;
import org.maxrio22.economyapi.domain.repository.CurrencyRepository;
import org.maxrio22.economyapi.infrastructure.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    private final DatabaseConnection database;

    public CurrencyRepositoryImpl(DatabaseConnection database) {
        this.database = database;
    }

    @Override
    public Currency getCurrencyById(int id) {
        String sql = "SELECT * FROM currencies WHERE id = ?";
        Currency currency = null; // 🔹 Inicializamos `currency` en `null`

        try (
                Connection conn = database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    currency = new Currency(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("symbol"),
                            rs.getFloat("exchange_rate"),
                            rs.getFloat("inflation_rate"),
                            rs.getString("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al obtener moneda con ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }

        if (currency == null) {
            Bukkit.getLogger().warning("⚠ Moneda con ID " + id + " no encontrada.");
        }

        return currency; // 🔹 Devuelve `null` si no se encontró la moneda
    }

    @Override
    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        String sql = "SELECT * FROM currencies";

        try (
                Connection conn = database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                currencies.add(new Currency(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("symbol"),
                        rs.getFloat("exchange_rate"),
                        rs.getFloat("inflation_rate"),
                        rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al obtener todas las monedas: " + e.getMessage());
            e.printStackTrace();
        }

        return currencies;
    }
}
