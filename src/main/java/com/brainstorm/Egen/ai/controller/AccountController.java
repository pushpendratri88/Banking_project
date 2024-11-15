package com.brainstorm.Egen.ai.controller;

import com.brainstorm.Egen.ai.data.AccountDTO;
import com.brainstorm.Egen.ai.data.ResponseDTO;
import com.brainstorm.Egen.ai.entity.Account;
import com.brainstorm.Egen.ai.entity.Transaction;
import com.brainstorm.Egen.ai.service.AccountService;
import com.brainstorm.Egen.ai.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/account/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    TransactionService transactionService;

    // Create a new account
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        Account createdAccount = accountService.createAccount(accountDTO);
//        accountDTO.setId(createdAccount.getId());
//        accountDTO.setAccountNumber(createdAccount.getAccountNumber());
//        accountDTO.setAccountType(createdAccount.getAccountType());
//        accountDTO.setBalance(createdAccount.getBalance());
//        accountDTO.setCustomerId(createdAccount.getCustomer().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("201","Account Created "));
    }

    // Get account by ID
    @GetMapping("/{accountId}/account")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Optional<Account> optionalaccount = accountService.getAccountById(accountId);
        return ResponseEntity.ok(optionalaccount.get());
    }

    // Get all accounts
    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<ResponseDTO> getAccountBalance(@PathVariable Long customerId) {
        Double accountBalance  = accountService.getAccountByCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("200","Account Balance : " +accountBalance));
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getAccountTransactionHistory(@PathVariable Long accountId) {
        List<Transaction> accountList = transactionService.findTransactionByAccountId(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(accountList);
    }

    // Update account details
    @PutMapping("/{accountId}/updateAccount")
    public ResponseEntity<Account> updateAccountDetails(@PathVariable Long accountId, @RequestBody Account accountDetails) {
        Account updatedAccount = accountService.updateAccountDetails(accountId, accountDetails);
        return ResponseEntity.ok(updatedAccount);
    }

    // Delete an account
    @DeleteMapping("/{accountId}/deleteAccount")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}
