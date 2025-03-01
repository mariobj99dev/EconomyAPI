package org.maxrio22.economyapi.infrastructure.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.maxrio22.economyapi.application.usecase.currency.GetAllCurrenciesUseCase;
import org.maxrio22.economyapi.application.usecase.currency.GetCurrencyUseCase;
import org.maxrio22.economyapi.domain.model.Currency;
import org.maxrio22.economyapi.domain.repository.CurrencyRepository;
import org.maxrio22.economyapi.infrastructure.database.DatabaseConnection;
import org.maxrio22.economyapi.infrastructure.persistance.CurrencyRepositoryImpl;

import java.util.List;

public final class Main extends JavaPlugin {
    private GetCurrencyUseCase getCurrencyUseCase;
    private GetAllCurrenciesUseCase getAllCurrenciesUseCase;
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("🚀 Activando EconomyAPI...");

        DatabaseConnection database = DatabaseConnection.getInstance();
        CurrencyRepository currencyRepository = new CurrencyRepositoryImpl(database);
        this.getCurrencyUseCase = new GetCurrencyUseCase(currencyRepository);
        this.getAllCurrenciesUseCase = new GetAllCurrenciesUseCase(currencyRepository);

        /*Currency currency = getCurrencyUseCase.execute(1);*/
        List<Currency> currencies = getAllCurrenciesUseCase.execute();

        currencies.forEach(currency -> {
            Bukkit.getLogger().info("El simbolo de la moneda " + currency.getName() + ", es: " + currency.getSymbol()+ ".");
        });

        /*System.out.println(currency.getSymbol());*/
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("🔌 Desactivando EconomyAPI...");
        DatabaseConnection.getInstance().close();
    }
}

