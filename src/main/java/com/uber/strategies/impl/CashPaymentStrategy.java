package com.uber.strategies.impl;

import com.uber.entities.Driver;
import com.uber.entities.Payment;
import com.uber.entities.Wallet;
import com.uber.entities.enums.PaymentStatus;
import com.uber.entities.enums.TransactionMethod;
import com.uber.repositories.PaymentRepository;
import com.uber.services.PaymentService;
import com.uber.services.WalletService;
import com.uber.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Rider  -> 100
//Driver -> 70 as 30 rs is platform commission

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    /*
    ┌─────┐
|  paymentServiceImpl defined in file [/home/pranav/Spring/Backend-Projects/Uber-app-backend/target/classes/com/uber/services/impl/PaymentServiceImpl.class]
↑     ↓
|  paymentStrategyManager defined in file [/home/pranav/Spring/Backend-Projects/Uber-app-backend/target/classes/com/uber/strategies/PaymentStrategyManager.class]
↑     ↓
|  cashPaymentStrategy defined in file [/home/pranav/Spring/Backend-Projects/Uber-app-backend/target/classes/com/uber/strategies/impl/CashPaymentStrategy.class]
└─────┘
     */
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Wallet driverWallet = walletService.findByUser(driver.getUser());

        double platformCommission = driverWallet.getBalance() * PLATFORM_COMMISSION;
        walletService.deductMoneyFromWallet(driver.getUser(), platformCommission, null, TransactionMethod.CASH ,payment.getRide());
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
