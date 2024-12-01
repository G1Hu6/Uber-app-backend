package com.uber.strategies.impl;

import com.uber.dto.RideRequestDto;
import com.uber.entities.Driver;
import com.uber.strategies.DriverMatchingStrategy;

import java.util.List;

public class DriverMatchingNearestDriverMatchingStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> getMatchingDrivers(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
