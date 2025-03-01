package org.maxrio22.economyapi.application.usecase.currency;

import org.maxrio22.economyapi.domain.model.Currency;
import org.maxrio22.economyapi.domain.repository.CurrencyRepository;

import java.util.List;

public class GetAllCurrenciesUseCase {
    private final CurrencyRepository repository;

    public GetAllCurrenciesUseCase(CurrencyRepository repository) {
        this.repository = repository;
    }

    public List<Currency> execute() {
        return repository.getAllCurrencies();
    }
}
