package com.swp.evmsystem.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean isCustomer(Integer userId);
}
