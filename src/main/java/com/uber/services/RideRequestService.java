package com.uber.services;

import com.uber.entities.RideRequest;
import org.springframework.stereotype.Service;

@Service
public interface RideRequestService {

    public RideRequest getRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
