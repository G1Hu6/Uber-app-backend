package com.uber.strategies;

import com.uber.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION = 0.35;
    void processPayment(Payment payment);
}
