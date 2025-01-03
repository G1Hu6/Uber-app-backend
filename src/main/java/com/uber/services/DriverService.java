package com.uber.services;


import com.uber.dto.DriverDto;
import com.uber.dto.RideDto;
import com.uber.dto.RiderDto;
import com.uber.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {

    RideDto acceptRide(Long rideRequestId);

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RideDto cancelRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer ratting);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRids(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, Boolean isAvailable);

    Driver createNewDriver(Driver driver);
}
