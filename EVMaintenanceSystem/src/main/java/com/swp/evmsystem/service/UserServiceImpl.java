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
        return userRepository.getRoleNameById(userId).equals("CUSTOMER");
    }

    @Override
    public void createUser(String username, String password, String roleName) {
        // TODO: Implement user creation logic
        // This should handle creating either CustomerEntity or EmployeeEntity based on role
        System.out.println("Creating user: " + username + " with role: " + roleName);
    }
}
