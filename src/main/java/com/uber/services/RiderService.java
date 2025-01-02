package com.uber.services;

import com.uber.dto.RideDto;
import com.uber.dto.RideRequestDto;
import com.uber.dto.RiderDto;
import com.uber.entities.Rider;
import com.uber.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    RiderDto rateDriver(Long rideId, Integer ratting);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRids(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
