package com.brainstorm.Egen.ai.service.impl;

import com.brainstorm.Egen.ai.data.AccountDTO;
import com.brainstorm.Egen.ai.entity.Account;
import com.brainstorm.Egen.ai.repository.AccountRepository;
import com.brainstorm.Egen.ai.repository.CustomerRepository;
import com.brainstorm.Egen.ai.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public Account createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setAccountType(accountDTO.getAccountType());
        account.setBalance(accountDTO.getBalance());
        account.setCustomer(customerRepository.findById(accountDTO.getCustomerId()).get());
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        Optional<Account> OptionalAccount = accountRepository.findById(id);
        accountRepository.delete(OptionalAccount.get());
    }

    @Override
    public Account updateAccount(Long id, double ammount) {


        Optional<Account> optionalAccountDetails = accountRepository.findById(id);
        return accountRepository.findById(id).map(account -> {
            if(optionalAccountDetails.get().getAccountType().equals("credit")){
                account.setBalance(optionalAccountDetails.get().getBalance() + ammount);
            }
            else if(optionalAccountDetails.get().getAccountType().equals("debit")){
                account.setBalance(optionalAccountDetails.get().getBalance() - ammount);
            }
            optionalAccountDetails.get().setTransactionValue(optionalAccountDetails.get().getTransactionValue() + ammount);
            accountRepository.save(account);
            setRanking();
            return accountRepository.save(account);
        }).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public Account updateAccountDetails(Long id, Account accountDetails) {
        return accountRepository.findById(id).map(account -> {
            account.setAccountNumber(accountDetails.getAccountNumber());
            account.setAccountType(accountDetails.getAccountType());
            account.setBalance(accountDetails.getBalance());
            account.setDateOpened(accountDetails.getDateOpened());
            account.setIsActive(accountDetails.getIsActive());
            return accountRepository.save(account);
        }).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public Double getAccountByCustomerId(Long customerId) {
        List<Account> accountList = accountRepository.findAccountByCustomerId(customerId);
        return accountList.stream().collect(Collectors.summingDouble(acc -> acc.getBalance()));
    }

    private void setRanking(){
        AtomicInteger count = new AtomicInteger(1);
        getAllAccounts().stream().sorted(Comparator.comparingDouble(Account :: getTransactionValue).reversed()).forEach(account1 -> {
            account1.setRanking(count.getAndIncrement());
            accountRepository.save(account1);
        });
    }
}
