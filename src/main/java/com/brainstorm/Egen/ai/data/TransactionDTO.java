package com.brainstorm.Egen.ai.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private Double amount;
    private LocalDateTime transactionDate;
    private String transactionType;
    private String description;
    private Long accountId;
}
