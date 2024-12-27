package com.uber.services.impl;

import com.uber.dto.RideDto;
import com.uber.dto.RideRequestDto;
import com.uber.dto.RiderDto;
import com.uber.entities.RideRequest;
import com.uber.entities.Rider;
import com.uber.entities.User;
import com.uber.entities.enums.RideRequestStatus;
import com.uber.repositories.RideRequestRepository;
import com.uber.repositories.RiderRepository;
import com.uber.services.RiderService;
import com.uber.strategies.DriverMatchingStrategy;
import com.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;

    private final RideFareCalculationStrategy rideFareCalculationStrategy;

    private final DriverMatchingStrategy driverMatchingStrategy;

    private final RideRequestRepository rideRequestRepository;

    private final RiderRepository riderRepository;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        //log.info(rideRequest.toString());
        rideRequest.setRequestStatus(RideRequestStatus.PENDING);
        Double fare = rideFareCalculationStrategy.calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        driverMatchingStrategy.getMatchingDrivers(rideRequest);
        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateDriver(Long rideId, Integer ratting) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRids() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).ratting(0.0).build();
        return riderRepository.save(rider);
    }
}
