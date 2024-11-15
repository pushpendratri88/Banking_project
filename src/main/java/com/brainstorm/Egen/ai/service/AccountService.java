package com.brainstorm.Egen.ai.service;

import com.brainstorm.Egen.ai.data.AccountDTO;
import com.brainstorm.Egen.ai.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllAccounts();
    Optional<Account> getAccountById(Long accountId);
    Account createAccount(AccountDTO accountDTO);
    void deleteAccount(Long id);
    Account updateAccount(Long id, double amount);
    Account updateAccountDetails (Long id, Account accountDetails);
    Double getAccountByCustomerId(Long customerId);


}
