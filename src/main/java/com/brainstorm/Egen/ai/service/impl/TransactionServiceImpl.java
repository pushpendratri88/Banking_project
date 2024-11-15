package com.brainstorm.Egen.ai.service.impl;

import com.brainstorm.Egen.ai.data.TransactionDTO;
import com.brainstorm.Egen.ai.entity.Transaction;
import com.brainstorm.Egen.ai.repository.TransactionRepository;
import com.brainstorm.Egen.ai.service.AccountService;
import com.brainstorm.Egen.ai.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    AccountService accountService;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    public void createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionDTO.getTransactionType());
        transaction.setAmount(transactionDTO.getAmount());
        accountService.updateAccount(transactionDTO.getAccountId(), transactionDTO.getAmount());
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> findTransactionByAccountId(Long accountId) {
        return transactionRepository.findTransactionByAccountId(accountId);
    }


}
