package com.swp.evmsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Data
public class LoginResponse {
    String token;
    UserDTO user;
}