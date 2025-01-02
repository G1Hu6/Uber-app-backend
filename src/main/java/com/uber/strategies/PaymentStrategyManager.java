package com.uber.strategies;

import com.uber.entities.enums.PaymentMethod;
import com.uber.strategies.impl.CashPaymentStrategy;
import com.uber.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){

        return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
//         PaymentStrategy selectedPaymentStrategy = null;
//        switch (paymentMethod){
//            case WALLET:{
//                selectedPaymentStrategy = walletPaymentStrategy;
//            }
//            case CASH:{
//                selectedPaymentStrategy = cashPaymentStrategy;
//            }
//        }
    }
}
