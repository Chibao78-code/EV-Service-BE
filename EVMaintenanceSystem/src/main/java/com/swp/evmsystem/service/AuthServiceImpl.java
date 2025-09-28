package com.swp.evmsystem.service;

import com.swp.evmsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final jwtService jwtService;

    public String loginWithPhone(String phone, String password) {
        UserEntity user = userRepository.findByPhone(phone); // tìm user theo số điện thoại
        // kiểm tra user có tồn tại không
        if (user.isEmpty()) {
            throw new RuntimeException("Invalid phone number or password");
        }
        // kiểm tra mật khẩu
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid phone number or password");
        }
        // trả về JWT token
        return jwtService.generateToken(user);
    }

    public String loginWithEmail(String email, String password) {
        UserEntity user = userRepository.findByEmail(email); // tìm user theo email
        // kiểm tra user có tồn tại không
        if (user.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }
        // kiểm tra mật khẩu
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        // trả về JWT token
        return jwtService.generateToken(user);
    }

}
