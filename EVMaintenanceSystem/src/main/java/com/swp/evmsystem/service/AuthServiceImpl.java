package com.swp.evmsystem.service;

import com.swp.evmsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
}
