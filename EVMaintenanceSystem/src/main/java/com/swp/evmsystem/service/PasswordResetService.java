package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.PasswordResetTokenEntity;
import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.repository.PasswordResetTokenRepository;
import com.swp.evmsystem.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasswordResetService {
    
    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    
    @Value("${otp.expiration.minutes:5}")
    private int otpExpirationMinutes;
    
    @Value("${otp.length:6}")
    private int otpLength;
    //gui otp ve email
    @Transactional
    public void sendOTPToEmail(String email) throws MessagingException {
        // check xem co email trong database chua
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại trong hệ thống"));
        
        // random ma otp ngau nhien
        String otpCode = generateOTP();
        
        // tao otp xong va luu vao db
        PasswordResetTokenEntity token = PasswordResetTokenEntity.builder()
                .email(email)
                .otpCode(otpCode)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(otpExpirationMinutes))
                .isUsed(false)
                .build();
        
        tokenRepository.save(token);
        
        // gui otp ve email
        emailService.sendOTPEmail(email, otpCode);
    }
    //ktr otp
    @Transactional(readOnly = true)
    public boolean verifyOTP(String email, String otpCode) {
        // tim otp trong db 
        PasswordResetTokenEntity token = tokenRepository
                .findByEmailAndOtpCodeAndIsUsedFalse(email, otpCode)
                .orElse(null);
        
        if (token == null) {
            return false;
        }
        //check otp co hen han trong 5p ko
        if (token.isExpired()) {
            return false;
        }
        
        return true;
    }
    //dat lai mk neu dung otp
    @Transactional
    public void resetPassword(String email, String otpCode, String newPassword) {
        // checkotp
        PasswordResetTokenEntity token = tokenRepository
                .findByEmailAndOtpCodeAndIsUsedFalse(email, otpCode)
                .orElseThrow(() -> new RuntimeException("Mã OTP không hợp lệ hoặc đã được sử dụng"));
        
        // neu otp hen han
        if (token.isExpired()) {
            throw new RuntimeException("Mã OTP đã hết hạn");
        }
        
        // lay user theo email
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
        
        // doi mk moi va ma hoa khong cho decode mk neu bi hack tren frontend
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        
        // danh dau otp da su dung
        token.setIsUsed(true);
        token.setUsedAt(LocalDateTime.now());
        tokenRepository.save(token);
    }
    //random otp ngau nhien
    private String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));
        }
        
        return otp.toString();
    }
    //xoa otp het han 5p
    @Transactional
    public void cleanupExpiredTokens() {
        tokenRepository.deleteByExpiresAtBefore(LocalDateTime.now());
    }
}
