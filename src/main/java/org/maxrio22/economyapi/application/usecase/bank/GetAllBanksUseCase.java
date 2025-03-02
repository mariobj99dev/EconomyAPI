package org.maxrio22.economyapi.application.usecase.bank;

import org.maxrio22.economyapi.domain.model.Bank;
import org.maxrio22.economyapi.domain.repository.BankRepository;

import java.util.List;

public class GetAllBanksUseCase {
    private final BankRepository repository;

    public GetAllBanksUseCase(BankRepository repository) {
        this.repository = repository;
    }

    public List<Bank> execute() {
        return repository.getAllBanks();
    }
}
