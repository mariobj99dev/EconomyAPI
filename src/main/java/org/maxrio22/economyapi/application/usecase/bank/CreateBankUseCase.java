package org.maxrio22.economyapi.application.usecase.bank;

import org.maxrio22.economyapi.domain.model.Bank;
import org.maxrio22.economyapi.domain.repository.BankRepository;

public class CreateBankUseCase {
    private final BankRepository repository;

    public CreateBankUseCase(BankRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Bank bank){
        return repository.createBank(bank);
    }
}
