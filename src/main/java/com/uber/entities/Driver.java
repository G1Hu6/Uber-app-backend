package com.uber.entities;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "driver",
        indexes = {
                @Index(name = "idx_driver_vehicle_id", columnList = "vehicleId")
        }
)
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double ratting;

    private Boolean available;

    private String vehicleId;

    @Column(columnDefinition = "Geometry(Point,4326)",name = "current_location")
    private Point currentLocation;
}
