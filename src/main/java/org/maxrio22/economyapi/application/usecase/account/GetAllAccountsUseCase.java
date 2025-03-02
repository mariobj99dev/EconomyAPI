package org.maxrio22.economyapi.application.usecase.account;

import org.maxrio22.economyapi.domain.model.Account;
import org.maxrio22.economyapi.domain.repository.AccountRepository;

import java.util.List;

public class GetAllAccountsUseCase {
    private final AccountRepository repository;

    public GetAllAccountsUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> execute() {
        return repository.getAllAccounts();
    }
}
