package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.UserDTO;
import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.mapper.UserMapper;
import com.swp.evmsystem.repository.UserRepository;
import com.swp.evmsystem.security.UserEntityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper mapper;
    @Override
    public boolean isCustomer(Integer userId) {
        return repository.getRoleNameById(userId).equals("CUSTOMER");
    }

    @Override
    public boolean createUser(UserEntity user) {
        return repository.save(user) != null;
    }

    @Override
    public UserDTO getUserByUserDetail(UserEntityDetails userDetail) { // Get user information from UserEntityDetails
        if (userDetail == null || userDetail.getUser() == null) { // Check if userDetail or user is null
            return null;
        }
        return mapper.toDTO(userDetail.getUser()); // Convert UserEntity to UserDTO and return it
    }
}
