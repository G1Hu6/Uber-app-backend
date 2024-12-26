package com.uber.strategies.impl;

import com.uber.dto.RideRequestDto;
import com.uber.entities.Driver;
import com.uber.entities.RideRequest;
import com.uber.strategies.DriverMatchingStrategy;

import java.util.List;

public class DriverMatchingHighestRatedDriverMatchingStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> getMatchingDrivers(RideRequest rideRequest) {
        return List.of();
    }
}
