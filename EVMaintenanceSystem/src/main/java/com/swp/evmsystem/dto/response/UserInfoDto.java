package com.swp.evmsystem.dto.response;

import com.swp.evmsystem.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Schema(description = "Thông tin người dùng")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Getter
public class UserInfoDto {
    int id;
    String name;
    String role;

    public UserInfoDto(UserEntity user) {
        this.id = user.getId();
        this.name = user.getFullName();
        this.role = user.getRole().getName().name();
    }
}
