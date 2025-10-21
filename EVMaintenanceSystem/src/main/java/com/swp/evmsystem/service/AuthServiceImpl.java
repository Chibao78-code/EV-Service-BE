package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.LoginRequestDTO;
import com.swp.evmsystem.dto.response.LoginResponseDTO;
import com.swp.evmsystem.dto.response.UserInforDTO;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.EmployeeEntity;
import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.repository.UserRepository;
import com.swp.evmsystem.security.JwtService;
import com.swp.evmsystem.security.UserEntityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements  AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponseDTO login(AuthenticationManager authenticationManager, JwtService jwtService, LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserEntityDetails userDetails = (UserEntityDetails) authentication.getPrincipal(); // Take UserDetails
        UserEntity user = userDetails.getUserEntity(); // Take UserEntity for Building DTO
        UserInforDTO.UserInforDTOBuilder builder = UserInforDTO.builder().role(user.getRole().name()); // Builder UserInforDTO

        if(user instanceof CustomerEntity customer) {
            builder.fullName(customer.getFullName());
        } else if (user instanceof EmployeeEntity employee) {
            builder.fullName(employee.getFullName());
        } // Check to take name of cus or emp.

        String token = jwtService.generateToken(userDetails);

        return new LoginResponseDTO(token, builder.build());
    }

    @Override
    public Boolean isCustomer(UserEntityDetails userDetails) {
        return userDetails.getUserEntity() instanceof CustomerEntity;
    }
}
