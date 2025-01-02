package com.uber.strategies.impl;

import com.uber.entities.Driver;
import com.uber.entities.Payment;
import com.uber.entities.Rider;
import com.uber.entities.enums.PaymentStatus;
import com.uber.entities.enums.TransactionMethod;
import com.uber.repositories.PaymentRepository;
import com.uber.services.PaymentService;
import com.uber.services.WalletService;
import com.uber.services.impl.PaymentServiceImpl;
import com.uber.strategies.PaymentStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Rider had 232 , Driver had 500
//Ride cost is 100, commision = 35
//Rider -> 232-100 = 132
//Driver -> 500 + (100 - 35) = 565

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    //private final PaymentService paymentService;
    //Here we are getting loop hole
    /*
    ┌─────┐
|  paymentServiceImpl defined in file [/home/pranav/Spring/Backend-Projects/Uber-app-backend/target/classes/com/uber/services/impl/PaymentServiceImpl.class]
↑     ↓
|  paymentStrategyManager defined in file [/home/pranav/Spring/Backend-Projects/Uber-app-backend/target/classes/com/uber/strategies/PaymentStrategyManager.class]
↑     ↓
|  walletPaymentStrategy defined in file [/home/pranav/Spring/Backend-Projects/Uber-app-backend/target/classes/com/uber/strategies/impl/WalletPaymentStrategy.class]

     */

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(),null, TransactionMethod.RIDE, payment.getRide());

        double driverCut = payment.getAmount() * (1-PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(), driverCut, null, TransactionMethod.RIDE,payment.getRide());
        // Set payment status to CONFIRMED
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
