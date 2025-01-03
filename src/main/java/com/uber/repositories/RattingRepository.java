package com.uber.repositories;

import com.uber.entities.Driver;
import com.uber.entities.Ratting;
import com.uber.entities.Ride;
import com.uber.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RattingRepository extends JpaRepository<Ratting, Long> {

    List<Ratting> findByDriver(Driver driver);
    List<Ratting> findByRider(Rider rider);

    Ratting findByRide(Ride ride);
}
