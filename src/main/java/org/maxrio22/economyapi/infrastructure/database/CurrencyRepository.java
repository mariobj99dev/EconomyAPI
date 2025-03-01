package org.maxrio22.economyapi.infrastructure.database;

import org.maxrio22.economyapi.core.domain.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyRepository {
    private final DatabaseConnection database;

    public CurrencyRepository() {
        this.database = DatabaseConnection.getInstance();
    }

    public Currency getAccountById(int id) {
        String sql = "SELECT * FROM currency WHERE id = ?";
        try {
            Connection conn = database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, String.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Currency(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("symbol").charAt(0),
                    rs.getFloat("exchangeRate"),
                    rs.getFloat("inflationRate"),
                    rs.getString("createdAt")
                );
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
