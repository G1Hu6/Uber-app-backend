package com.uber.services.impl;

import com.uber.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImpl implements DistanceService {

    @Override
    public double calculateDistance(Point source, Point destination) {
        return 0;
    }
}
