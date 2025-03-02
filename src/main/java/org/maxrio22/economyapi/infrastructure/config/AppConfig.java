package org.maxrio22.economyapi.infrastructure.config;

import org.maxrio22.economyapi.application.usecase.account.*;
import org.maxrio22.economyapi.application.usecase.bank.*;
import org.maxrio22.economyapi.application.usecase.currency.*;
import org.maxrio22.economyapi.domain.repository.AccountRepository;
import org.maxrio22.economyapi.domain.repository.BankRepository;
import org.maxrio22.economyapi.domain.repository.CurrencyRepository;
import org.maxrio22.economyapi.infrastructure.database.DatabaseConnection;
import org.maxrio22.economyapi.infrastructure.persistance.AccountRepositoryImpl;
import org.maxrio22.economyapi.infrastructure.persistance.BankRepositoryImpl;
import org.maxrio22.economyapi.infrastructure.persistance.CurrencyRepositoryImpl;

public class AppConfig {
    private final DatabaseConnection database;
    private final CurrencyRepository currencyRepository;
    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;
    // Currency Use Cases initialization
    public final GetCurrencyByIdUseCase getCurrencyByIdUseCase;
    public final GetAllCurrenciesUseCase getAllCurrenciesUseCase;
    public final CreateCurrencyUseCase createCurrencyUseCase;
    public final UpdateCurrencyUseCase updateCurrencyUseCase;
    public final DeleteCurrencyUseCase deleteCurrencyUseCase;

    // Bank Use Cases initialization
    public final GetBankByIdUseCase getBankByIdUseCase;
    public final GetAllBanksUseCase getAllBanksUseCase;
    public final CreateBankUseCase createBankUseCase;
    public final UpdateBankUseCase updateBankUseCase;
    public final DeleteBankUseCase deleteBankUseCase;

    // Account Use Cases initialization
    public final GetAccountByIdUseCase getAccountByIdUseCase;
    public final GetAccountByIbanUseCase getAccountByIbanUseCase;
    public final GetAllAccountsUseCase getAllAccountsUseCase;
    public final CreateAccountUseCase createAccountUseCase;
    public final UpdateAccountUseCase updateAccountUseCase;
    public final DeleteAccountUseCase deleteAccountUseCase;

    public AppConfig() {
        this.database = DatabaseConnection.getInstance();
        this.currencyRepository = new CurrencyRepositoryImpl(database);
        this.bankRepository = new BankRepositoryImpl(database);

        // CUIDADO QUE TIENES QUE PASARLE BANK REPOSITORY A TODA LA APP
        this.accountRepository = new AccountRepositoryImpl(database, bankRepository);

        // Currency Use Cases
        this.getCurrencyByIdUseCase = new GetCurrencyByIdUseCase(currencyRepository);
        this.getAllCurrenciesUseCase = new GetAllCurrenciesUseCase(currencyRepository);
        this.createCurrencyUseCase = new CreateCurrencyUseCase(currencyRepository);
        this.updateCurrencyUseCase = new UpdateCurrencyUseCase(currencyRepository);
        this.deleteCurrencyUseCase = new DeleteCurrencyUseCase(currencyRepository);

        // Bank Use Cases
        this.getBankByIdUseCase = new GetBankByIdUseCase(bankRepository);
        this.getAllBanksUseCase = new GetAllBanksUseCase(bankRepository);
        this.createBankUseCase = new CreateBankUseCase(bankRepository);
        this.updateBankUseCase = new UpdateBankUseCase(bankRepository);
        this.deleteBankUseCase = new DeleteBankUseCase(bankRepository);

        // Account Use Cases
        this.getAccountByIdUseCase = new GetAccountByIdUseCase(accountRepository);
        this.getAccountByIbanUseCase = new GetAccountByIbanUseCase(accountRepository);
        this.getAllAccountsUseCase = new GetAllAccountsUseCase(accountRepository);
        this.createAccountUseCase = new CreateAccountUseCase(accountRepository, bankRepository);
        this.updateAccountUseCase = new UpdateAccountUseCase(accountRepository);
        this.deleteAccountUseCase = new DeleteAccountUseCase(accountRepository);
    }
}
