package com.uber.services;


import com.uber.dto.DriverDto;
import com.uber.dto.RiderDto;
import com.uber.entities.Ride;

public interface RattingService {

    DriverDto rateDriver(Ride ride, Integer ratting);
    RiderDto rateRider(Ride ride, Integer ratting);
    void createNewRatting(Ride ride);
}
