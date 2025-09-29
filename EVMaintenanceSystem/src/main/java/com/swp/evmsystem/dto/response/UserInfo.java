package com.swp.evmsystem.dto.response;

import com.swp.evmsystem.entity.RoleEntity;
import com.swp.evmsystem.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "Thông tin người dùng")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserInfo {
    int id;
    String name;
    RoleEntity role;

    public UserInfo(UserEntity user) {
        this.id = user.getId();
        this.name = user.getFullName();
        this.role = user.getRole();
    }
}
