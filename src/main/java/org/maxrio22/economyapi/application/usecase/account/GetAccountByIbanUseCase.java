package org.maxrio22.economyapi.application.usecase.account;

import org.maxrio22.economyapi.domain.model.Account;
import org.maxrio22.economyapi.domain.repository.AccountRepository;

public class GetAccountByIbanUseCase {
    private final AccountRepository repository;

    public GetAccountByIbanUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public Account execute(String iban) {
        return repository.getAccountByIban(iban);
    }
}
