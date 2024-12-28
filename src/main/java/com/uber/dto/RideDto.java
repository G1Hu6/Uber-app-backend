package com.uber.dto;

import com.uber.entities.enums.PaymentMethod;
import com.uber.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {

    private Long id;

    private RiderDto rider;

    private DriverDto driver;

    private PointDto pickUpLocation;

    private PointDto dropOffLocation;

    private LocalDateTime createdTime;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private Double fare;

    private String otp;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;
}
