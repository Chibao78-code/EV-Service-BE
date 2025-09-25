package com.swp.evmsystem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Schema(description = "số điện thoại hoặc email", example = "0123456789", required = true)
    private String username;
    @Schema(description = "Mật khẩu", example = "123456", required = true)
    private String password;
}