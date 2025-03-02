package org.maxrio22.economyapi.application.usecase.bank;

import org.bukkit.Bukkit;
import org.maxrio22.economyapi.domain.model.Account;
import org.maxrio22.economyapi.domain.model.Bank;
import org.maxrio22.economyapi.domain.repository.AccountRepository;
import org.maxrio22.economyapi.domain.repository.BankRepository;

public class CreateBankUseCase {
    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;

    public CreateBankUseCase(BankRepository bankRepository, AccountRepository accountRepository) {
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
    }

    public boolean execute(Bank bank) {
        int bankId = bankRepository.createBank(bank);

        if (bankId == -1) {
            Bukkit.getLogger().severe("❌ Error al crear el banco.");
            return false;
        }

        Bukkit.getLogger().info("✅ Banco '" + bank.getName() + "' creado con éxito con ID: " + bankId);

        // Crear la cuenta bancaria asociada usando el ID correcto
        Account bankAccount = new Account(
                0, null, "bank", "bank_" + bankId, bankId, 0.0f, "bank", false, null
        );

        if (accountRepository.createAccount(bankAccount)) {
            Bukkit.getLogger().info("✅ Cuenta bancaria para el banco '" + bank.getName() + "' creada con éxito.");
            return true;
        } else {
            Bukkit.getLogger().severe("❌ Error al crear la cuenta bancaria para el banco '" + bank.getName() + "'.");
            return false;
        }
    }
    /*public boolean execute(Bank bank){
        return bankRepository.createBank(bank);

    }*/
}
