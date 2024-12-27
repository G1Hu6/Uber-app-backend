package com.uber.repositories;

import com.uber.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    // ST_DISTANCE(point1, point2)
    // ST_DWITHIN(point1, point2)

    // This query is optimized by itself because it uses indexing
    @Query(value = "SELECT d.*,ST_Distance(d.current_location, :pickUpLocation) AS distance "+
            "FROM drivers d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickUpLocation, 10000) " +
            "ORDER BY distance " +
            "LIMIT 10",nativeQuery = true
    )
    List<Driver> findTenNearestDrivers(Point pickUpLocation, Point dropOffLocation);
}
