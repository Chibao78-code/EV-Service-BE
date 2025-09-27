package com.swp.evmsystem.dto.response;

import com.swp.evmsystem.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Schema(description = "Phản hồi sau khi đăng nhập thành công")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class LoginResponse {
    @Schema(description = "JWT token dùng để xác thực các request tiếp theo",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    String tokenAcess;
    @Schema(description = "Thong tin người dùng",
            implementation = UserInfo.class)
    UserInfo user;
}