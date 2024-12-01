package com.uber.strategies.impl;

import com.uber.dto.RideRequestDto;
import com.uber.strategies.RideFareCalculationStrategy;

public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
