package org.maxrio22.economyapi.application.usecase.account;

import org.maxrio22.economyapi.domain.model.Account;
import org.maxrio22.economyapi.domain.repository.AccountRepository;

public class UpdateAccountUseCase {
    private final AccountRepository repository;

    public UpdateAccountUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Account account) {
        return repository.updateAccount(account);
    }
}
