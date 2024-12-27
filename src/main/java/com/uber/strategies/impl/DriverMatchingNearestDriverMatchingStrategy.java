package com.uber.strategies.impl;

import com.uber.dto.RideRequestDto;
import com.uber.entities.Driver;
import com.uber.entities.RideRequest;
import com.uber.repositories.DriverRepository;
import com.uber.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverMatchingStrategy implements DriverMatchingStrategy {


    private final DriverRepository driverRepository;

    @Override
    public List<Driver> getMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickUpLocation(), rideRequest.getDropOffLocation());
    }


}
