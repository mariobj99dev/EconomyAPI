package org.maxrio22.economyapi.infrastructure.persistance;

import org.bukkit.Bukkit;
import org.maxrio22.economyapi.domain.model.Account;
import org.maxrio22.economyapi.domain.repository.AccountRepository;
import org.maxrio22.economyapi.domain.repository.BankRepository;
import org.maxrio22.economyapi.infrastructure.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {
    private final DatabaseConnection database;
    private final BankRepository bankRepository;

    public AccountRepositoryImpl(DatabaseConnection database, BankRepository bankRepository) {
        this.database = database;
        this.bankRepository = bankRepository;
    }

    @Override
    public Account getAccountById(int id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        Account account = null;

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    account = mapResultSetToAccount(rs);
                }
            }
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al obtener cuenta con ID " + id + ": " + e.getMessage());
            e.printStackTrace();
        }

        if (account == null) {
            Bukkit.getLogger().warning("⚠ Cuenta con ID " + id + " no encontrada.");
        }

        return account;
    }

    @Override
    public Account getAccountByIban(String iban) {
        String sql = "SELECT * FROM accounts WHERE iban = ?";
        Account account = null;

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, iban);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    account = mapResultSetToAccount(rs);
                }
            }
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al obtener cuenta con IBAN " + iban + ": " + e.getMessage());
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                accounts.add(mapResultSetToAccount(rs));
            }
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al obtener todas las cuentas: " + e.getMessage());
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public boolean createAccount(Account account) {
        if (account.getIban() == null || account.getIban().isEmpty()) {
            String generatedIban = generateIban(account.getBankId(),account.getOwnerType());
            if (generatedIban == null) {
                Bukkit.getLogger().severe("❌ No se pudo generar el IBAN para el banco con ID " + account.getBankId());
                return false;
            }
            account.setIban(generatedIban);
        }

        String sql = "INSERT INTO accounts (iban, owner_type, owner_id, bank_id, balance, type, blocked) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, account.getIban());
            stmt.setString(2, account.getOwnerType());
            stmt.setString(3, account.getOwnerId());
            stmt.setInt(4, account.getBankId());
            stmt.setFloat(5, account.getBalance());
            stmt.setString(6, account.getType());
            stmt.setBoolean(7, account.isBlocked());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        account.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al crear la cuenta bancaria: " + e.getMessage());
        }

        return false;
    }


    @Override
    public boolean updateAccount(Account account) {
        String sql = "UPDATE accounts SET owner_type = ?, owner_id = ?, bank_id = ?, balance = ?, type = ?, blocked = ? WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, account.getOwnerType());
            stmt.setString(2, account.getOwnerId());
            stmt.setInt(3, account.getBankId());
            stmt.setFloat(4, account.getBalance());
            stmt.setString(5, account.getType());
            stmt.setBoolean(6, account.isBlocked());
            stmt.setInt(7, account.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                Bukkit.getLogger().warning("⚠ No se encontró la cuenta con ID " + account.getId() + " para actualizar.");
                return false;
            }
            return true;
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al actualizar la cuenta con ID " + account.getId() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAccount(int id) {
        String sql = "DELETE FROM accounts WHERE id = ?";

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al eliminar la cuenta con ID " + id + ": " + e.getMessage());
        }

        return false;
    }

    private Account mapResultSetToAccount(ResultSet rs) throws SQLException {
        return new Account(
                rs.getInt("id"),
                rs.getString("iban"),
                rs.getString("owner_type"),
                rs.getString("owner_id"),
                rs.getInt("bank_id"),
                rs.getFloat("balance"),
                rs.getString("type"),
                rs.getBoolean("blocked"),
                rs.getString("created_at")
        );
    }

    private int getNextAccountNumber(int bankId, String ibanPrefix, String year) {
        String sql = "SELECT SUBSTRING_INDEX(iban, '-', -1) AS account_number " +
                "FROM accounts " +
                "WHERE bank_id = ? AND iban LIKE ? " +
                "ORDER BY CAST(account_number AS UNSIGNED) ASC";

        List<Integer> existingNumbers = new ArrayList<>();

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bankId);
            stmt.setString(2, ibanPrefix + "-" + String.format("%02d", bankId) + "-" + year + "-%"); // Filtrar por tipo, banco y año
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    existingNumbers.add(rs.getInt("account_number"));
                }
            }
        } catch (SQLException e) {
            Bukkit.getLogger().severe("❌ Error al obtener los números de cuenta para el banco " + bankId + ": " + e.getMessage());
        }

        // Buscar el primer número de IBAN faltante en la secuencia
        int expectedNumber = 1;
        for (int num : existingNumbers) {
            if (num != expectedNumber) {
                return expectedNumber; // Se encontró un hueco, usar este número
            }
            expectedNumber++;
        }

        return expectedNumber;
    }

    private String generateIban(int bankId, String accountType) {
        if (bankRepository.getBankById(bankId) == null) {
            Bukkit.getLogger().severe("❌ Error: El banco con ID " + bankId + " no existe.");
            return null;
        }

        // Determinar el prefijo según el tipo de cuenta
        String ibanPrefix;
        switch (accountType.toLowerCase()) {
            case "bank":
                ibanPrefix = "BANK";
                break;
            case "player":
                ibanPrefix = "PLAY";
                break;
            case "company":
                ibanPrefix = "COMP";
                break;
            case "government":
                ibanPrefix = "GOVT";
                break;
            default:
                Bukkit.getLogger().severe("❌ Tipo de cuenta desconocido: " + accountType);
                return null;
        }

        String bankCode = String.format("%02d", bankId); // ID del banco formateado a 2 dígitos
        String year = String.valueOf(java.time.Year.now().getValue()); // Año actual
        int accountNumber = getNextAccountNumber(bankId, ibanPrefix, year); // Obtener el número de cuenta disponible

        return String.format("%s-%s-%s-%04d", ibanPrefix, bankCode, year, accountNumber);
    }
}
