package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.LoginRequestDTO;
import com.swp.evmsystem.dto.response.LoginResponseDTO;
import com.swp.evmsystem.security.JwtService;
import com.swp.evmsystem.security.UserEntityDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    LoginResponseDTO login(AuthenticationManager authenticationManager, JwtService jwtService, LoginRequestDTO loginRequestDTO);

    Boolean isCustomer(UserEntityDetails userEntityDetails);

}
