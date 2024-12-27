package com.uber.services.impl;

import com.uber.dto.DriverDto;
import com.uber.dto.SignUpDto;
import com.uber.dto.UserDto;
import com.uber.entities.Rider;
import com.uber.entities.User;
import com.uber.entities.enums.Role;
import com.uber.exceptions.RuntimeConflictException;
import com.uber.repositories.RiderRepository;
import com.uber.repositories.UserRepository;
import com.uber.services.AuthService;
import com.uber.services.RiderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RiderService riderService;
    @Autowired
    private RiderRepository riderRepository;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signUp(SignUpDto signUpDTO) {
        User user = userRepository.findByEmail(signUpDTO.getEmail()).orElse(null);
        if(user != null){
            throw new RuntimeConflictException("Failed Signup, Email is already exists by " + signUpDTO.getEmail());
        }
        User mappedUser = modelMapper.map(signUpDTO, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        // Onboard User to database create user related entities
        Rider rider = riderService.createNewRider(savedUser);
        // TODO add wallet related service here

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardDriver(Long userId) {
        return null;
    }
}
