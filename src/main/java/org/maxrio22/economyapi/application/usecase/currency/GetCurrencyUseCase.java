package org.maxrio22.economyapi.application.usecase.currency;

import org.maxrio22.economyapi.domain.model.Currency;
import org.maxrio22.economyapi.domain.repository.CurrencyRepository;

public class GetCurrencyUseCase {
    private final CurrencyRepository repository;

    public GetCurrencyUseCase(CurrencyRepository repository) {
        this.repository = repository;
    }

    public Currency execute(int id) {
        return repository.getCurrencyById(id);
    }
}