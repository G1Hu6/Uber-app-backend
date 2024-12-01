package com.uber.services;

import com.uber.dto.DriverDto;
import com.uber.dto.RideDto;
import com.uber.dto.RiderDto;

import java.util.List;

public interface RiderService {

    RideDto requestRide(Long rideId);

    RideDto cancelRide(Long rideId);

    RiderDto rateDriver(Long rideId, Integer ratting);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRids();
}
