package com.uber.repositories;

import com.uber.entities.Driver;
import com.uber.entities.Ride;
import com.uber.entities.Rider;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    void findByRider(Rider rider, PageRequest pageRequest);

    void findByDriver(Driver driver, PageRequest pageRequest);
}
