package com.uber.services.impl;

import com.uber.dto.DriverDto;
import com.uber.dto.RideDto;
import com.uber.dto.RiderDto;
import com.uber.entities.Driver;
import com.uber.entities.Ride;
import com.uber.entities.RideRequest;
import com.uber.entities.enums.RideRequestStatus;
import com.uber.entities.enums.RideStatus;
import com.uber.exceptions.ResourceNotFoundException;
import com.uber.repositories.DriverRepository;
import com.uber.services.DriverService;
import com.uber.services.RideRequestService;
import com.uber.services.RideService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final DriverRepository driverRepository;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.getRideRequestById(rideRequestId);

        if(!rideRequest.getRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new ResourceNotFoundException("Ride Request can not be accepted , Ride Request Status : " + rideRequest.getRequestStatus() );
        }
        Driver currentDriver = getCurrentDriver();
        if(!currentDriver.getAvailable()){
            throw new ResourceNotFoundException("Driver cannot accept ride due to unavailability" + rideRequest.getRequestStatus() );
        }

        currentDriver.setAvailable(false);
        Driver savedDriver = driverRepository.save(currentDriver);
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDto.class);
    }

    @Override
    public RideDto startRide(Long rideId, String otp) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver canot start ride as he has not accepted it earlier");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Driver status is not CONFIRMED hence cannot be stared, status : " + ride.getRideStatus());
        }

        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("Otp is Not valid : " + otp);
        }
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer ratting) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRids() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(()->new ResourceNotFoundException("Driver is Not found for id : " + 2));
    }


}
