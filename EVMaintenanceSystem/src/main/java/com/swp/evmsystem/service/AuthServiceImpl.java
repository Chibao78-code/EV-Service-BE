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

}
