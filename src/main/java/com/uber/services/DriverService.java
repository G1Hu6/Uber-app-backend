package com.uber.services;


import com.uber.dto.DriverDto;
import com.uber.dto.RideDto;
import com.uber.dto.RiderDto;

import java.util.List;

public interface DriverService {

    RideDto acceptRide(Long rideId);

    RideDto startRide(Long rideId);

    RideDto endRide(Long rideId);

    RideDto cancelRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer ratting);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRids();
}
