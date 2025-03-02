package org.maxrio22.economyapi.application.usecase.account;


import org.maxrio22.economyapi.domain.model.Account;
import org.maxrio22.economyapi.domain.repository.AccountRepository;
import org.maxrio22.economyapi.domain.repository.BankRepository;

public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    public CreateAccountUseCase(AccountRepository accountRepository, BankRepository bankRepository) {
        this.accountRepository = accountRepository;
        this.bankRepository = bankRepository;
    }

    public boolean execute(Account account) {
        if (bankRepository.getBankById(account.getBankId()) == null) {
            return false; // No se puede crear una cuenta en un banco que no existe
        }
        return accountRepository.createAccount(account);
    }
}

