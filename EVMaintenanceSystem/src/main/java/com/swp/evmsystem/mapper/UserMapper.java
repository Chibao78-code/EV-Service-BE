package com.swp.evmsystem.mapper;

import com.swp.evmsystem.dto.response.UserDTO;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.EmployeeEntity;
import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Entity -> DTO
    @Mapping(source = "role", target = "role", qualifiedByName = "roleToString")
    @Mapping(source = "id", target = "userId")
    UserDTO toDTO(UserEntity entity);

    UserEntity toEntity(UserDTO dto);

    @Named("roleToString")
    default String roleToString(Role role) {
        if (role == null) {
            return "";
        }

        return role.toString();
    }

    @ObjectFactory
    default UserEntity createUserEntity(UserDTO dto) {
        if (dto == null || dto.getRole() == null) {
            throw new IllegalArgumentException("userType must not be null");
        }

        return switch (dto.getRole().toUpperCase()) {
            case "CUSTOMER" -> new CustomerEntity();
            case "EMPLOYEE" -> new EmployeeEntity();
            default -> throw new IllegalArgumentException("Unknown user type: " + dto.getRole());
        };
    }
}
