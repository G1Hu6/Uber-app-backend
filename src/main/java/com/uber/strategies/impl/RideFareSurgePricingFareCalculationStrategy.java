package com.uber.strategies.impl;

import com.uber.dto.RideRequestDto;
import com.uber.entities.RideRequest;
import com.uber.strategies.RideFareCalculationStrategy;

public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {



    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}
