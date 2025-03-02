package org.maxrio22.economyapi.application.usecase.bank;

import org.maxrio22.economyapi.domain.model.Bank;
import org.maxrio22.economyapi.domain.repository.BankRepository;

public class UpdateBankUseCase {
    private final BankRepository repository;

    public UpdateBankUseCase(BankRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Bank bank) {
        return repository.updateBank(bank);
    }
}
