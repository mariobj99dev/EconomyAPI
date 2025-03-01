package org.maxrio22.economyapi.infrastructure.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.maxrio22.economyapi.application.usecase.currency.*;
import org.maxrio22.economyapi.domain.repository.CurrencyRepository;
import org.maxrio22.economyapi.infrastructure.database.DatabaseConnection;
import org.maxrio22.economyapi.infrastructure.persistance.CurrencyRepositoryImpl;
import org.maxrio22.economyapi.presentation.commands.currency.*;

import java.util.Objects;

public final class Main extends JavaPlugin {
    private GetCurrencyUseCase getCurrencyUseCase;
    private GetAllCurrenciesUseCase getAllCurrenciesUseCase;
    private CreateCurrencyUseCase createCurrencyUseCase;
    private UpdateCurrencyUseCase updateCurrencyUseCase;
    private DeleteCurrencyUseCase deleteCurrencyUseCase;
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("🚀 Activando EconomyAPI...");

        DatabaseConnection database = DatabaseConnection.getInstance();
        CurrencyRepository currencyRepository = new CurrencyRepositoryImpl(database);
        this.getCurrencyUseCase = new GetCurrencyUseCase(currencyRepository);
        this.getAllCurrenciesUseCase = new GetAllCurrenciesUseCase(currencyRepository);
        this.createCurrencyUseCase = new CreateCurrencyUseCase(currencyRepository);
        this.updateCurrencyUseCase = new UpdateCurrencyUseCase(currencyRepository);
        this.deleteCurrencyUseCase = new DeleteCurrencyUseCase(currencyRepository);

        Objects.requireNonNull(getCommand("getCurrency")).setExecutor(new GetCurrencyCommand(getCurrencyUseCase));
        Objects.requireNonNull(getCommand("getCurrencies")).setExecutor(new GetAllCurrenciesCommand(getAllCurrenciesUseCase));
        Objects.requireNonNull(getCommand("createcurrency")).setExecutor(new CreateCurrencyCommand(createCurrencyUseCase));
        Objects.requireNonNull(getCommand("updatecurrency")).setExecutor(new UpdateCurrencyCommand(updateCurrencyUseCase));
        Objects.requireNonNull(getCommand("deletecurrency")).setExecutor(new DeleteCurrencyCommand(deleteCurrencyUseCase));
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("🔌 Desactivando EconomyAPI...");
        DatabaseConnection.getInstance().close();
    }
}

