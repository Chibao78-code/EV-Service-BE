package com.swp.evmsystem.service;

import com.swp.evmsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean isCustomer(Integer userId) {
        return userRepository.getRoleNameByUserId(userId).equals("CUSTOMER");
    }

    @Override
    public String getNameByPhoneOrEmail(String phoneOrEmail) {
        return userRepository.getNameByPhoneOrEmail(phoneOrEmail);
    }
}
