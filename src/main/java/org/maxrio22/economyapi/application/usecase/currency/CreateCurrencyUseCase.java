package org.maxrio22.economyapi.application.usecase.currency;

import org.maxrio22.economyapi.domain.model.Currency;
import org.maxrio22.economyapi.domain.repository.CurrencyRepository;

public class CreateCurrencyUseCase {
    private final CurrencyRepository repository;

    public CreateCurrencyUseCase(CurrencyRepository repository) {
        this.repository = repository;
    }

    public boolean execute(Currency currency) {
        return repository.createCurrency(currency);
    }
}
