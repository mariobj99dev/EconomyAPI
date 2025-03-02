package org.maxrio22.economyapi.application.usecase.bank;

import org.maxrio22.economyapi.domain.repository.BankRepository;

public class DeleteBankUseCase {
    private final BankRepository repository;

    public DeleteBankUseCase(BankRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int id) {
        return repository.deleteBank(id);
    }
}
