package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.request.LoginRequestDTO;
import com.swp.evmsystem.dto.response.LoginResponseDTO;
import com.swp.evmsystem.dto.response.UserDTO;
import com.swp.evmsystem.security.JwtService;
import com.swp.evmsystem.security.UserEntityDetails;
import com.swp.evmsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );    // Bước 1: Xác thực username/password qua AuthenticationManager
        // Bước 2: Nếu xác thực thành công → lấy thông tin người dùng
        UserEntityDetails userDetails = (UserEntityDetails) authentication.getPrincipal();
        // Bước 3: Sinh JWT token cho người dùng
        String token = jwtTokenProvider.generateToken(userDetails);
        // Bước 4: Lấy thông tin người dùng ở dạng DTO để trả về client
        UserDTO userDto = userService.getUserByUserDetail(userDetails);
        // Bước 5: Gộp token và thông tin người dùng vào LoginResponseDTO
        LoginResponseDTO lg = new LoginResponseDTO(token, userDto);
        // Bước 6: Trả về response 200 OK cùng với token và user info
        return ResponseEntity.ok(lg);
    }
}
