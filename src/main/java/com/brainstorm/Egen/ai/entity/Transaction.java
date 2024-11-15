package com.brainstorm.Egen.ai.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private LocalDateTime transactionDate;
    private String transactionType; // e.g., Deposit, Withdrawal, Transfer
    private String description;

    // Many Transactions belong to one Account
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


}
