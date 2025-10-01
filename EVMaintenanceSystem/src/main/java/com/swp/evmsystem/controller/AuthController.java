package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.request.LoginRequest;
import com.swp.evmsystem.dto.response.LoginResponse;
import com.swp.evmsystem.dto.response.UserInfo;
import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.security.JwtTokenProvider;
import com.swp.evmsystem.security.UserEntityDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Đăng nhập bằng số điện thoại hoặc email và mật khẩu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Đăng nhập thành công",
                    content = @Content(schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "403", description = "Sai thông tin đăng nhập")
    })
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserEntityDetails userDetails = (UserEntityDetails) authentication.getPrincipal();
        UserEntity user = userDetails.getUser();
        String token = jwtTokenProvider.generateToken(userDetails);
        UserInfo userInfo = new UserInfo(user);
        return new LoginResponse(token, userInfo);
    }
}
