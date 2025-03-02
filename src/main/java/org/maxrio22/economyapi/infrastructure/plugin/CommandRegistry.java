package org.maxrio22.economyapi.infrastructure.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.maxrio22.economyapi.infrastructure.config.AppConfig;
import org.maxrio22.economyapi.presentation.commands.account.*;
import org.maxrio22.economyapi.presentation.commands.account.tabcompleters.*;
import org.maxrio22.economyapi.presentation.commands.bank.*;
import org.maxrio22.economyapi.presentation.commands.bank.tabcompleters.*;
import org.maxrio22.economyapi.presentation.commands.currency.*;
import org.maxrio22.economyapi.presentation.commands.currency.tabcompleters.*;

import java.util.Objects;

public class CommandRegistry {
    public static void registerCommands(JavaPlugin plugin, AppConfig config) {
        // Currency Commands
        Objects.requireNonNull(plugin.getCommand("getcurrency")).setExecutor(new GetCurrencyByIdCommand(config.getCurrencyByIdUseCase));
        Objects.requireNonNull(plugin.getCommand("getcurrency")).setTabCompleter(new GetCurrencyByIdTab());

        Objects.requireNonNull(plugin.getCommand("getcurrencies")).setExecutor(new GetAllCurrenciesCommand(config.getAllCurrenciesUseCase));

        Objects.requireNonNull(plugin.getCommand("createcurrency")).setExecutor(new CreateCurrencyCommand(config.createCurrencyUseCase));
        Objects.requireNonNull(plugin.getCommand("createcurrency")).setTabCompleter(new CreateCurrencyTab());

        Objects.requireNonNull(plugin.getCommand("updatecurrency")).setExecutor(new UpdateCurrencyCommand(config.updateCurrencyUseCase));
        Objects.requireNonNull(plugin.getCommand("updatecurrency")).setTabCompleter(new UpdateCurrencyTab());

        Objects.requireNonNull(plugin.getCommand("deletecurrency")).setExecutor(new DeleteCurrencyCommand(config.deleteCurrencyUseCase));
        Objects.requireNonNull(plugin.getCommand("deletecurrency")).setTabCompleter(new DeleteCurrencyTab());

        // Bank Commands
        Objects.requireNonNull(plugin.getCommand("getbank")).setExecutor(new GetBankByIdCommand(config.getBankByIdUseCase));
        Objects.requireNonNull(plugin.getCommand("getbank")).setTabCompleter(new GetBankByIdTab());

        Objects.requireNonNull(plugin.getCommand("getbanks")).setExecutor(new GetAllBanksCommand(config.getAllBanksUseCase));

        Objects.requireNonNull(plugin.getCommand("createbank")).setExecutor(new CreateBankCommand(config.createBankUseCase));
        Objects.requireNonNull(plugin.getCommand("createbank")).setTabCompleter(new CreateBankTab());

        Objects.requireNonNull(plugin.getCommand("updatebank")).setExecutor(new UpdateBankCommand(config.updateBankUseCase));
        Objects.requireNonNull(plugin.getCommand("updatebank")).setTabCompleter(new UpdateBankTab());

        Objects.requireNonNull(plugin.getCommand("deletebank")).setExecutor(new DeleteBankCommand(config.deleteBankUseCase));
        Objects.requireNonNull(plugin.getCommand("deletebank")).setTabCompleter(new DeleteBankTab());

        // Account Commands
        Objects.requireNonNull(plugin.getCommand("getaccount")).setExecutor(new GetAccountByIdCommand(config.getAccountByIdUseCase));
        Objects.requireNonNull(plugin.getCommand("getaccount")).setTabCompleter(new GetAccountByIdTab());

        Objects.requireNonNull(plugin.getCommand("getaccountbyiban")).setExecutor(new GetAccountByIbanCommand(config.getAccountByIbanUseCase));
        Objects.requireNonNull(plugin.getCommand("getaccountbyiban")).setTabCompleter(new GetAccountByIbanTab());

        Objects.requireNonNull(plugin.getCommand("getaccounts")).setExecutor(new GetAllAccountsCommand(config.getAllAccountsUseCase));

        Objects.requireNonNull(plugin.getCommand("createaccount")).setExecutor(new CreateAccountCommand(config.createAccountUseCase));
        Objects.requireNonNull(plugin.getCommand("createaccount")).setTabCompleter(new CreateAccountTab());

        Objects.requireNonNull(plugin.getCommand("updateaccount")).setExecutor(new UpdateAccountCommand(config.updateAccountUseCase, config.getAccountByIdUseCase));
        Objects.requireNonNull(plugin.getCommand("updateaccount")).setTabCompleter(new UpdateAccountTab());

        Objects.requireNonNull(plugin.getCommand("deleteaccount")).setExecutor(new DeleteAccountCommand(config.deleteAccountUseCase));
        Objects.requireNonNull(plugin.getCommand("deleteaccount")).setTabCompleter(new DeleteAccountTab());
    }
}
