package com.swp.evmsystem.mapper;

import com.swp.evmsystem.dto.response.UserDTO;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.EmployeeEntity;
import com.swp.evmsystem.entity.RoleEntity;
import com.swp.evmsystem.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Entity -> DTO
    @Mapping(source = "role", target = "roleName", qualifiedByName = "roleToString")
    @Mapping(source = "id", target = "userId")
    UserDTO toDTO(UserEntity entity);

    // DTO -> Entity
    @Mapping(source = "userId", target = "id")


    UserEntity toEntity(UserDTO dto);

    @Named("roleToString")
    default String roleToString(RoleEntity role) {
        if (role == null) {
            return "";
        }

        return role.getName().name();
    }

    @ObjectFactory
    default UserEntity createUserEntity(UserDTO dto) {
        if (dto == null || dto.getRoleName() == null) {
            throw new IllegalArgumentException("userType must not be null");
        }

        return switch (dto.getRoleName().toUpperCase()) {
            case "CUSTOMER" -> new CustomerEntity();
            case "EMPLOYEE" -> new EmployeeEntity();
            default -> throw new IllegalArgumentException("Unknown user type: " + dto.getRoleName());
        };
    }
}
