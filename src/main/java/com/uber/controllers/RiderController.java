package com.uber.controllers;

import com.uber.dto.*;
import com.uber.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rider")
@RequiredArgsConstructor
@Secured("ROLE_RIDER")
public class RiderController {

    // @RequiredArgsConstructor annotation is used to define constructor to inject dependency.
    private final RiderService riderService;

    @PostMapping(path = "/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
    }

    @PostMapping(path = "/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping(path = "/rateDriver")
    public ResponseEntity<DriverDto> rateDriver(@RequestBody RattingDto rattingDto){
        return ResponseEntity.ok(riderService.rateDriver(rattingDto.getRideId(), rattingDto.getRatting()));
    }

    @GetMapping(path = "/getMyProfile")
    public ResponseEntity<RiderDto> getMyProfile(){
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping(path = "getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                       @RequestParam(defaultValue = "10", required = false) Integer pageSize){
        return ResponseEntity.ok(riderService.getAllMyRids(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdTime", "id"))));
    }
}
