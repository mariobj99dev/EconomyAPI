package org.maxrio22.economyapi.domain.repository;

import org.maxrio22.economyapi.domain.model.Account;

import java.util.List;

public interface AccountRepository {
    Account getAccountById(int id);
    Account getAccountByIban(String iban);
    List<Account> getAllAccounts();
    boolean createAccount(Account account);
    boolean updateAccount(Account account);
    boolean deleteAccount(int id);
}
