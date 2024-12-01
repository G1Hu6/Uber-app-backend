package com.uber.strategies;

import com.uber.dto.RideRequestDto;
import com.uber.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> getMatchingDrivers(RideRequestDto rideRequestDto);
}
