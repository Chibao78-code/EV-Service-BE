package com.swp.evmsystem.service;

public interface UserService {
    boolean isCustomer(Integer userId);
    void createUser(String username, String password, String roleName);
}
