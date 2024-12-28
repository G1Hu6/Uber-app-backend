package com.uber.strategies.impl;

import com.uber.entities.RideRequest;
import com.uber.services.DistanceService;
import com.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    private final static double SURGE_FACTOR = 2;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        Double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation(), rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}
