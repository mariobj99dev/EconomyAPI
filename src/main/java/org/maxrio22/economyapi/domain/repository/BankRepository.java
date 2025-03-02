package org.maxrio22.economyapi.domain.repository;


import org.maxrio22.economyapi.domain.model.Bank;

import java.util.List;

public interface BankRepository {
    Bank getBankById(int id);
    List<Bank> getAllBanks();
    boolean createBank(Bank Bank);
    boolean updateBank(Bank Bank);
    boolean deleteBank(int id);
}
