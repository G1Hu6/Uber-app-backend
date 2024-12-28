package com.uber.dto;

import com.uber.entities.enums.PaymentMethod;
import com.uber.entities.enums.RideRequestStatus;
import lombok.*;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RideRequestDto {

    private Long id;

    private RiderDto rider;

    private PointDto pickUpLocation;

    private PointDto dropOffLocation;

    private LocalDateTime requestedTime;

    private Double fare;

    private PaymentMethod paymentMethod;

    private RideRequestStatus requestStatus;
}
