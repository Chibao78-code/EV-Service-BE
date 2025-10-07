package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean isCustomer(Integer userId);
    boolean createUser(UserEntity user);
}
