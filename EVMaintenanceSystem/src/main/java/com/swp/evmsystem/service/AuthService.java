package com.swp.evmsystem.service;

public interface AuthService {
    String loginWithPhone(String phone, String password);
    String loginWithEmail(String email, String password);
}
