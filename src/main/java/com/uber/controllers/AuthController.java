package com.uber.controllers;

import com.uber.dto.DriverDto;
import com.uber.dto.OnBoardDriverDto;
import com.uber.dto.SignUpDto;
import com.uber.dto.UserDto;
import com.uber.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){
        return new ResponseEntity<>(authService.signUp(signUpDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "/onBoardNewDriver/{userId}")
    public ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnBoardDriverDto onBoardDriverDto ){
        return new ResponseEntity<>(authService.onboardDriver(userId, onBoardDriverDto.getVehicleId()), HttpStatus.CREATED);
    }
}
