package org.maxrio22.economyapi.application.usecase.account;

import org.maxrio22.economyapi.domain.model.Account;
import org.maxrio22.economyapi.domain.repository.AccountRepository;

public class GetAccountByIdUseCase {
    private final AccountRepository repository;

    public GetAccountByIdUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public Account execute(int id) {
        return repository.getAccountById(id);
    }
}
