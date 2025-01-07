package com.uber.services;

import com.uber.dto.DriverDto;
import com.uber.dto.SignUpDto;
import com.uber.dto.UserDto;

public interface AuthService {

    String[] login(String email, String password);

    UserDto signUp(SignUpDto signUpDTO);

    DriverDto onboardDriver(Long userId, String vehicleId);
}
