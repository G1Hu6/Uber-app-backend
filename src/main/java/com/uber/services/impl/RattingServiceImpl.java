package com.uber.services.impl;

import com.uber.dto.DriverDto;
import com.uber.dto.RiderDto;
import com.uber.entities.Driver;
import com.uber.entities.Ratting;
import com.uber.entities.Ride;
import com.uber.entities.Rider;
import com.uber.repositories.DriverRepository;
import com.uber.repositories.RattingRepository;
import com.uber.repositories.RiderRepository;
import com.uber.services.RattingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RattingServiceImpl implements RattingService{

    private final RattingRepository rattingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer ratting) {
        Driver driver = ride.getDriver();
        Ratting rattingObj = rattingRepository.findByRide(ride);
        rattingObj.setDriverRatting(ratting);
        rattingRepository.save(rattingObj);

        Double totalAverageRatting =  rattingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(Ratting::getDriverRatting)
                .average()
                .orElse(0.0);

        driver.setRatting(totalAverageRatting);
        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Integer ratting) {
        Rider rider = ride.getRider();
        Ratting rattingObj = rattingRepository.findByRide(ride);
        rattingObj.setRiderRatting(ratting);
        rattingRepository.save(rattingObj);

        Double totalAverageRatting =  rattingRepository.findByRider(rider)
                .stream()
                .mapToDouble(Ratting::getRiderRatting)
                .average()
                .orElse(0.0);

        rider.setRatting(totalAverageRatting);
        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void createNewRatting(Ride ride) {
        Ratting ratting = Ratting.builder()
                .driver(ride.getDriver())
                .ride(ride)
                .rider(ride.getRider())
                .build();
        rattingRepository.save(ratting);
    }

}
