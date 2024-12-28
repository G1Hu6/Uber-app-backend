package com.uber.strategies.impl;

import com.uber.entities.Driver;
import com.uber.entities.RideRequest;
import com.uber.repositories.DriverRepository;
import com.uber.strategies.DriverMatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingHighestRatedDriverMatchingStrategy implements DriverMatchingStrategy {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearestTopRatedDrivers(rideRequest.getPickUpLocation());
    }
}
