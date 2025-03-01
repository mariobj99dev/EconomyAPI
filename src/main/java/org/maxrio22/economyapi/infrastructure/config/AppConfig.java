package org.maxrio22.economyapi.infrastructure.config;

import org.maxrio22.economyapi.application.usecase.currency.*;
import org.maxrio22.economyapi.domain.repository.CurrencyRepository;
import org.maxrio22.economyapi.infrastructure.database.DatabaseConnection;
import org.maxrio22.economyapi.infrastructure.persistance.CurrencyRepositoryImpl;

public class AppConfig {
    private final DatabaseConnection database;
    private final CurrencyRepository currencyRepository;

    private final GetCurrencyUseCase getCurrencyUseCase;
    private final GetAllCurrenciesUseCase getAllCurrenciesUseCase;
    private final CreateCurrencyUseCase createCurrencyUseCase;
    private final UpdateCurrencyUseCase updateCurrencyUseCase;
    private final DeleteCurrencyUseCase deleteCurrencyUseCase;

    public AppConfig() {
        this.database = DatabaseConnection.getInstance();
        this.currencyRepository = new CurrencyRepositoryImpl(database);

        this.getCurrencyUseCase = new GetCurrencyUseCase(currencyRepository);
        this.getAllCurrenciesUseCase = new GetAllCurrenciesUseCase(currencyRepository);
        this.createCurrencyUseCase = new CreateCurrencyUseCase(currencyRepository);
        this.updateCurrencyUseCase = new UpdateCurrencyUseCase(currencyRepository);
        this.deleteCurrencyUseCase = new DeleteCurrencyUseCase(currencyRepository);
    }

    public GetCurrencyUseCase getGetCurrencyUseCase() {
        return getCurrencyUseCase;
    }

    public GetAllCurrenciesUseCase getGetAllCurrenciesUseCase() {
        return getAllCurrenciesUseCase;
    }

    public CreateCurrencyUseCase getCreateCurrencyUseCase() {
        return createCurrencyUseCase;
    }

    public UpdateCurrencyUseCase getUpdateCurrencyUseCase() {
        return updateCurrencyUseCase;
    }

    public DeleteCurrencyUseCase getDeleteCurrencyUseCase() {
        return deleteCurrencyUseCase;
    }

    public DatabaseConnection getDatabase() {
        return database;
    }
}
