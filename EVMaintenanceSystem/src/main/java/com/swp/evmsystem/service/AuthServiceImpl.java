package com.swp.evmsystem.service.impl;

import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.repository.UserRepository;
import com.swp.evmsystem.service.AuthService;
import com.swp.evmsystem.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Đánh dấu class này là 1 Spring Service (bean quản lý nghiệp vụ)
@RequiredArgsConstructor // Lombok tự sinh constructor với tất cả các field final
public class AuthServiceImpl implements AuthService {

    // Các dependency được inject vào nhờ @RequiredArgsConstructor
    private final UserRepository userRepository;   // Dùng để truy vấn thông tin user trong DB
    private final PasswordEncoder passwordEncoder; // Dùng để so sánh mật khẩu mã hóa
    private final JwtService jwtService;           // Dùng để sinh JWT token

    @Override
    public String loginWithPhone(String phone, String password) {
        // Tìm user theo số điện thoại. Nếu không tìm thấy -> ném ngoại lệ
        UserEntity user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new BadCredentialsException("Invalid phone number or password"));

        // So sánh mật khẩu nhập vào với mật khẩu đã lưu trong DB (mã hóa)
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid phone number or password");
        }

        // Nếu hợp lệ -> sinh JWT token cho user
        return jwtService.generateToken(user);
    }

    @Override
    public String loginWithEmail(String email, String password) {
        // Tìm user theo email. Nếu không có -> ném ngoại lệ
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        // Kiểm tra mật khẩu nhập vào có khớp với mật khẩu mã hóa trong DB không
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        // Trả về JWT token nếu đăng nhập thành công
        return jwtService.generateToken(user);
    }
}
