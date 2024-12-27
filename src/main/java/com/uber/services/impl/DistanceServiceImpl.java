package com.uber.services.impl;

import com.uber.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceImpl implements DistanceService {

    private static final String OSRM_API_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point source, Point destination) {
        String uri = source.getX() + "," +source.getY() + ";" +destination.getX() + "," +destination.getY();
        // Call to third party api OSRM to calculate distance
        try{

            OSRMResponseDto osrmResponseDto = RestClient.builder()
                    .baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDto.class);

            // Dividing distance by 1000 to convert to KM form Meters
            return osrmResponseDto.getRoutes().getFirst().getDistance() / 1000.0;
        }catch (Exception e){
            throw new RuntimeException("Error while getting data from OSRM " + e.getMessage());
        }
    }
}

@Data
class OSRMResponseDto{
    private List<OSRMRoute> routes;
}

@Data
class OSRMRoute{
    private Double distance;
}
