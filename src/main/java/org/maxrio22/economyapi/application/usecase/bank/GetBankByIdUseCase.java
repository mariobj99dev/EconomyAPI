package org.maxrio22.economyapi.application.usecase.bank;

import org.maxrio22.economyapi.domain.model.Bank;
import org.maxrio22.economyapi.domain.repository.BankRepository;

public class GetBankByIdUseCase {
    private final BankRepository repository;

    public GetBankByIdUseCase(BankRepository repository) {
        this.repository = repository;
    }

    public Bank execute(int id) {
        return repository.getBankById(id);
    }
}
