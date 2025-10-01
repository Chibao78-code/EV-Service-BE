package com.swp.evmsystem.dto.request;

import com.swp.evmsystem.validation.annotation.ValidUsername;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Schema(description = "số điện thoại hoặc email", example = "0123456789", required = true)
    @ValidUsername
    private String username;
    @Schema(description = "Mật khẩu", example = "123456", required = true)
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
}