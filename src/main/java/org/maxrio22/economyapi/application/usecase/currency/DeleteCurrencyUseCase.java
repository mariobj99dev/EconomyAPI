package org.maxrio22.economyapi.application.usecase.currency;

import org.maxrio22.economyapi.domain.repository.CurrencyRepository;

public class DeleteCurrencyUseCase {
    private final CurrencyRepository repository;

    public DeleteCurrencyUseCase(CurrencyRepository repository) {
        this.repository = repository;
    }

    public boolean execute(int id) {
        return repository.deleteCurrency(id);
    }
}
