package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.UserDTO;
import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.security.UserEntityDetails;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean createUser(UserEntity user);

    int getAllCount();
}
