package com.brainstorm.Egen.ai.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private LocalDate dateOpened;
    private Boolean isActive;
    private Long customerId;
}
