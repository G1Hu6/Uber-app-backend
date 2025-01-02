package com.uber.services.impl;

import com.uber.entities.Ride;
import com.uber.entities.User;
import com.uber.entities.Wallet;
import com.uber.entities.WalletTransaction;
import com.uber.entities.enums.TransactionMethod;
import com.uber.entities.enums.TransactionType;
import com.uber.exceptions.ResourceNotFoundException;
import com.uber.repositories.WalletRepository;
import com.uber.services.WalletService;
import com.uber.services.WalletTransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final WalletTransactionService walletTransactionService;


    @Transactional
    @Override
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, TransactionMethod transactionMethod, Ride ride) {
        Wallet wallet = walletRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Wallet not found for user with user id : " + user.getId()));
        wallet.setBalance(wallet.getBalance()+amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .ride(ride)
                .wallet(wallet)
                .amount(amount)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, TransactionMethod transactionMethod, Ride ride) {
        Wallet wallet = walletRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Wallet not found for user with user id : " + user.getId()));
        wallet.setBalance(wallet.getBalance()-amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .ride(ride)
                .wallet(wallet)
                .amount(amount)
                .build();

        //walletTransactionService.createNewWalletTransaction(walletTransaction);
        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id : " + walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Wallet not found with user id : " + user.getId()));
    }


}
