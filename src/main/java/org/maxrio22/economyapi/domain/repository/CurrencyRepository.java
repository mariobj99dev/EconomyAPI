package org.maxrio22.economyapi.domain.repository;

import org.maxrio22.economyapi.domain.model.Currency;

import java.util.List;

public interface CurrencyRepository {
    Currency getCurrencyById(int id);
    List<Currency> getAllCurrencies();
}
