package com.brainstorm.Egen.ai.repository;

import com.brainstorm.Egen.ai.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId")
    List<Transaction> findTransactionByAccountId(Long accountId);
}
