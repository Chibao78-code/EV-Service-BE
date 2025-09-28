package com.swp.evmsystem.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}package com.swp.evmsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @Pattern(regexp = "^\\+?[0-9]{10,12}$", message = "Invalid phone number format")
    private String phone;

    @Email(message = "Invalid email format")
    private String email;

    private String loginType; // "phone", "email", or "username"
}