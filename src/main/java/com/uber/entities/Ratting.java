package com.uber.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(
        indexes = {
                @Index(name = "idx_ratting_rider",columnList = "rider_id"),
                @Index(name = "idx_ratting_driver",columnList = "driver_id"),
        }
)
public class Ratting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ride ride;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Driver driver;

    //ratting for drivers
    private Integer driverRatting;

    //ratting for riders
    private Integer riderRatting;
}
