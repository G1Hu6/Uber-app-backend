package com.uber.services.impl;

import com.uber.dto.DriverDto;
import com.uber.dto.SignUpDto;
import com.uber.dto.UserDto;
import com.uber.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signUp(SignUpDto signUpDTO) {
        return null;
    }

    @Override
    public DriverDto onboardDriver(Long userId) {
        return null;
    }
}
