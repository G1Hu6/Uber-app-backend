package com.uber.dto;

import com.uber.entities.Ride;
import com.uber.entities.enums.TransactionMethod;
import com.uber.entities.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransactionDto {

    private Long id;

    private double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private LocalDateTime createdAt;

    private  String transactionId;

    private WalletDto wallet;

    private Ride ride;
}
