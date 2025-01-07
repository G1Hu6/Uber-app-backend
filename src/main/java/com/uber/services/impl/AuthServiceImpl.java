package com.uber.services.impl;

import com.uber.dto.DriverDto;
import com.uber.dto.SignUpDto;
import com.uber.dto.UserDto;
import com.uber.entities.Driver;
import com.uber.entities.Rider;
import com.uber.entities.User;
import com.uber.entities.enums.Role;
import com.uber.exceptions.ResourceNotFoundException;
import com.uber.exceptions.RuntimeConflictException;
import com.uber.repositories.UserRepository;
import com.uber.security.JwtService;
import com.uber.services.AuthService;
import com.uber.services.DriverService;
import com.uber.services.RiderService;
import com.uber.services.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String[] login(String email, String password) {
        String[] tokens = new String[2];

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );
        // Try and Error
        if(authentication.isAuthenticated()){
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);
            tokens = new String[]{accessToken, refreshToken};
        }

        return tokens;
    }

    @Override
    @Transactional  // Used to avoid inconsistency
    public UserDto signUp(SignUpDto signUpDTO) {
        User user = userRepository.findByEmail(signUpDTO.getEmail()).orElse(null);
        if(user != null){
            throw new RuntimeConflictException("Failed Signup, Email is already exists by " + signUpDTO.getEmail());
        }
        User mappedUser = modelMapper.map(signUpDTO, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        // Encrypt password
        mappedUser.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        User savedUser = userRepository.save(mappedUser);

        // Onboard User to database create user related entities
        // use @Transactional to avoid inconsistency of user and rider
        Rider rider = riderService.createNewRider(savedUser);

        // TODO add wallet related service here
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardDriver(Long userId, String vehicleId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found with id + " + userId));
         if(user.getRoles().contains(Role.DRIVER)){
             throw new RuntimeConflictException("User with user id " + userId + " is already a DRIVER");
         }
         Driver createDriver = Driver.builder()
                  .user(user)
                  .ratting(0.0)
                  .vehicleId(vehicleId)
                  .available(true)
                  .build();

         user.getRoles().add(Role.DRIVER);
         userRepository.save(user);

         Driver savedDriver = driverService.createNewDriver(createDriver);
         return modelMapper.map(savedDriver, DriverDto.class);
    }
}
