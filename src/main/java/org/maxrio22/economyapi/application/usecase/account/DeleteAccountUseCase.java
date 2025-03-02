package org.maxrio22.economyapi.application.usecase.account;

import org.maxrio22.economyapi.domain.model.Account;
import org.maxrio22.economyapi.domain.repository.AccountRepository;

public class DeleteAccountUseCase {
    private final AccountRepository repository;

    public DeleteAccountUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int id) {
        return repository.deleteAccount(id);
    }
}
