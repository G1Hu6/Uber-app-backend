package com.uber.services;

import com.uber.entities.Ride;
import com.uber.entities.User;
import com.uber.entities.Wallet;
import com.uber.entities.enums.TransactionMethod;
import jakarta.transaction.Transactional;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, TransactionMethod transactionMethod, Ride ride);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, TransactionMethod transactionMethod, Ride ride);
}
