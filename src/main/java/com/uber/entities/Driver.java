package com.uber.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double ratting;

    @JsonProperty("available")
    private Double isAvailable;

    @Column(columnDefinition = "Geometry(Point,4326)",name = "current_location")
    private Point currentLocation;
}
