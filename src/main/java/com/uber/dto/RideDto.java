package com.uber.dto;

import com.uber.entities.Driver;
import com.uber.entities.Rider;
import com.uber.entities.enums.PaymentMethod;
import com.uber.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {

    private Long id;

    private RiderDto rider;

    private DriverDto driver;

    private Point pickUpLocation;

    private Point dropOffLocation;

    private LocalDateTime createdTime;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private Double fare;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;
}
