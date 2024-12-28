package com.uber.controllers;

import com.uber.dto.SignUpDto;
import com.uber.dto.UserDto;
import com.uber.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/signup")
    UserDto signUp(@RequestBody SignUpDto signUpDto){
        return authService.signUp(signUpDto);
    }
}
