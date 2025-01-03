package com.uber.services;

import com.uber.dto.DriverDto;
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

    DriverDto rateDriver(Long rideId, Integer ratting);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRids(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}

//10 ratting Count -> 4.0
//new rating 4.6
//updated ratting
//new ratting 4.0+4.6/11 = 4.05
