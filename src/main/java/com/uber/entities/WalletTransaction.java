package com.uber.entities;

import com.uber.entities.enums.TransactionMethod;
import com.uber.entities.enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private  String transactionId;

    @ManyToOne
    private Wallet wallet;

    @OneToOne
    private Ride ride;
}
