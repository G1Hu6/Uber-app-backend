package com.uber.entities;

import com.uber.entities.enums.TransactionMethod;
import com.uber.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(
        indexes = {
                @Index(name = "idx_wallet_wallet_transaction", columnList = "wallet_id"),
                @Index(name = "idx_wallet_ride", columnList = "ride_id"),
        }
)
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

    @ManyToOne
    private Ride ride;
}
