package com.swp.evmsystem.dto.response;

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
    String id;
    String name;
    String role;
}
