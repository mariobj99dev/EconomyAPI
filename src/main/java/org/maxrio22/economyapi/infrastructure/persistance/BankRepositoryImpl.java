package org.maxrio22.economyapi.infrastructure.persistance;

import org.bukkit.Bukkit;
import org.maxrio22.economyapi.domain.model.Bank;
import org.maxrio22.economyapi.domain.repository.BankRepository;
import org.maxrio22.economyapi.infrastructure.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements BankRepository {
    private final DatabaseConnection database;

    public BankRepositoryImpl(DatabaseConnection database) {
        this.database = database;
    }

    @Override
    public Bank getBankById(int id) {
        String sql = "SELECT * FROM banks WHERE id = ?";
        Bank bank = null;

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    bank = mapResultSetToBank(rs);
                }
            }
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al obtener banco con ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }

        if (bank == null) {
            Bukkit.getLogger().warning("⚠ Banco con ID " + id + " no encontrado.");
        }

        return bank;
    }

    @Override
    public List<Bank> getAllBanks() {
        List<Bank> banks = new ArrayList<>();
        String sql = "SELECT * FROM banks";

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                banks.add(mapResultSetToBank(rs));
            }
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al obtener todos los bancos: " + e.getMessage());
            e.printStackTrace();
        }

        return banks;
    }

    @Override
    public int createBank(Bank bank) {
        String sql = "INSERT INTO banks (name, owner_account, currency_id, reserve_ratio, interest_rate, loan_interest_rate, transaction_fee, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int bankId = -1; // Valor por defecto en caso de error

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, bank.getName());
            stmt.setString(2, bank.getOwnerAccount());
            stmt.setInt(3, bank.getCurrencyId());
            stmt.setFloat(4, bank.getReserveRatio());
            stmt.setFloat(5, bank.getInterestRate());
            stmt.setFloat(6, bank.getLoanInterestRate());
            stmt.setFloat(7, bank.getTransactionFee());
            stmt.setString(8, bank.getStatus());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        bankId = generatedKeys.getInt(1); // Obtener el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al crear el banco: " + e.getMessage());
        }

        return bankId;
    }


    @Override
    public boolean updateBank(Bank bank) {
        String sql = "UPDATE banks SET name = ?, owner_account = ?, currency_id = ?, reserve_ratio = ?, interest_rate = ?, loan_interest_rate = ?, transaction_fee = ?, status = ? WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bank.getName());
            stmt.setString(2, bank.getOwnerAccount());
            stmt.setInt(3, bank.getCurrencyId());
            stmt.setFloat(4, bank.getReserveRatio());
            stmt.setFloat(5, bank.getInterestRate());
            stmt.setFloat(6, bank.getLoanInterestRate());
            stmt.setFloat(7, bank.getTransactionFee());
            stmt.setString(8, bank.getStatus());
            stmt.setInt(9, bank.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al actualizar el banco con ID " + bank.getId() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBank(int id) {
        String sql = "DELETE FROM banks WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al eliminar el banco con ID " + id + ": " + e.getMessage());
            return false;
        }
    }

    private Bank mapResultSetToBank(ResultSet rs) throws SQLException {
        return new Bank(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("owner_account"),
                rs.getInt("currency_id"),
                rs.getFloat("reserve_ratio"),
                rs.getFloat("interest_rate"),
                rs.getFloat("loan_interest_rate"),
                rs.getFloat("transaction_fee"),
                rs.getString("status"),
                rs.getString("created_at")
        );
    }
}
