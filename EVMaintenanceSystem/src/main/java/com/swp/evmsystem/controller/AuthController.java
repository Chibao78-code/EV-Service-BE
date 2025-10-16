package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.request.LoginRequestDTO;
import com.swp.evmsystem.dto.response.LoginResponseDTO;
import com.swp.evmsystem.security.JwtService;
import com.swp.evmsystem.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtService jwtTokenProvider;
    @Autowired
    private AuthService authService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = authService.login(authenticationManager, jwtTokenProvider, request);
        return ResponseEntity.ok(response);
    }
}
