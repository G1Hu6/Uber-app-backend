package com.uber.services.impl;

import com.uber.dto.RideDto;
import com.uber.dto.RideRequestDto;
import com.uber.dto.RiderDto;
import com.uber.entities.*;
import com.uber.entities.enums.RideRequestStatus;
import com.uber.entities.enums.RideStatus;
import com.uber.exceptions.ResourceNotFoundException;
import com.uber.repositories.RideRequestRepository;
import com.uber.repositories.RiderRepository;
import com.uber.services.DriverService;
import com.uber.services.RideService;
import com.uber.services.RiderService;
import com.uber.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;

    private final RideStrategyManager rideStrategyManager;

    private final RideRequestRepository rideRequestRepository;

    private final RiderRepository riderRepository;

    private final RideService rideService;

    private final DriverService driverService;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        //Implement Spring Security to get current Rider
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        //log.info(rideRequest.toString());
        rideRequest.setRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRatting()).getMatchingDrivers(rideRequest);

        // TODO Send Notifications to all Drivers about this request
        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Ride does not own this ride with id : " + rideId);
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride cannot be cancelled , status : " + ride.getRideStatus());
        }
        Ride savedRide = rideService.updateRideStatus(ride,RideStatus.CANCELED);
        driverService.updateDriverAvailability(ride.getDriver(), true);

        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public RiderDto rateDriver(Long rideId, Integer ratting) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        Rider rider = getCurrentRider();
        return modelMapper.map(rider, RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRids(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider,pageRequest).map(
                ride -> modelMapper.map(ride, RideDto.class)
        );
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).ratting(0.0).build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        // TODO : Implement Spring Security to get current Rider
        return riderRepository.findById(1L).orElseThrow(() ->new ResourceNotFoundException(
                "Rider Not found by Id : " + 1));
    }
}
