package com.uber.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance = 0.0;

    @OneToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.DETACH)
    private User user;

    @OneToMany(mappedBy = "wallet")
    private List<WalletTransaction> transactions;
}
