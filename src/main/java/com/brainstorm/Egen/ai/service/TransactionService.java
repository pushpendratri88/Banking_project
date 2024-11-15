package com.brainstorm.Egen.ai.service;

import com.brainstorm.Egen.ai.data.TransactionDTO;
import com.brainstorm.Egen.ai.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
     List<Transaction> getAllTransactions();
     Optional<Transaction> getTransactionById(Long id);
     void createTransaction(TransactionDTO transactionDto);
     void deleteTransaction(Long id);
     List<Transaction> findTransactionByAccountId(Long accountId);
}
