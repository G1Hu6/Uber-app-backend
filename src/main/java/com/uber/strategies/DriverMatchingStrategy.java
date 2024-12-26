package com.uber.strategies;

import com.uber.dto.RideRequestDto;
import com.uber.entities.Driver;
import com.uber.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> getMatchingDrivers(RideRequest rideRequest);
}
