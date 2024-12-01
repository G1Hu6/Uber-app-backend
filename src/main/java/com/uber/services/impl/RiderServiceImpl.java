package com.uber.services.impl;

import com.uber.dto.RideDto;
import com.uber.dto.RiderDto;
import com.uber.services.RiderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {
    @Override
    public RideDto requestRide(Long rideId) {
        return null;
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
}
