package com.uber.strategies;

import com.uber.strategies.impl.DriverMatchingHighestRatedDriverMatchingStrategy;
import com.uber.strategies.impl.DriverMatchingNearestDriverMatchingStrategy;
import com.uber.strategies.impl.RideFareDefaultFareCalculationStrategy;
import com.uber.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingHighestRatedDriverMatchingStrategy driverMatchingHighestRatedDriverMatchingStrategy;
    private final DriverMatchingNearestDriverMatchingStrategy driverMatchingNearestDriverMatchingStrategy;
    private final RideFareDefaultFareCalculationStrategy rideFareDefaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy rideFareSurgePricingFareCalculationStrategy;


    public DriverMatchingStrategy driverMatchingStrategy(Double riderRating){
        if(riderRating >= 4.0){
            return driverMatchingHighestRatedDriverMatchingStrategy;
        }else{
            return driverMatchingNearestDriverMatchingStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        // 6PM to 10PM
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(22,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
        if(isSurgeTime){
            return rideFareSurgePricingFareCalculationStrategy;
        }else{
            return rideFareDefaultFareCalculationStrategy;
        }
    }
}
