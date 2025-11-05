package com.swp.evmsystem.service;

public class PasswordResetService {
    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${otp.expiration.minutes:5}")
    private int otpExpirationMinutes;

    //guiotp
    @Transactional
    public void sendOTPToEmail(String email) throws MessagingException {
        // Kiểm tra email có tồn tại trong hệ thống không
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email không tồn tại trong hệ thống"));

    }
    // Random otp
    String otpCode = generateOTP();

    // Lưu OTP vào database
    PasswordResetTokenEntity token = PasswordResetTokenEntity.builder()
            .email(email)
            .otpCode(otpCode)
            .createdAt(LocalDateTime.now())
            .expiresAt(LocalDateTime.now().plusMinutes(otpExpirationMinutes))
            .isUsed(false)
            .build();

        tokenRepository.save(token);

    // Gửi email chứa OTP
        emailService.sendOTPEmail(email, otpCode);
}
//xac thuc otp
@Transactional(readOnly = true)
public boolean verifyOTP(String email, String otpCode) {
    // tim toekn
    PasswordResetTokenEntity token = tokenRepository
            .findByEmailAndOtpCodeAndIsUsedFalse(email, otpCode)
            .orElse(null);

    if (token == null) {
        return false;
    }

    // Kiểm tra xem OTP đã hết hạn chưa
    if (token.isExpired()) {
        return false;
    }

    return true;
}
//dat lai mk
@Transactional
public void resetPassword(String email, String otpCode, String newPassword) {
    // Xác thực OTP
    PasswordResetTokenEntity token = tokenRepository
            .findByEmailAndOtpCodeAndIsUsedFalse(email, otpCode)
            .orElseThrow(() -> new RuntimeException("Mã OTP không hợp lệ hoặc đã được sử dụng"));

    // Kiểm tra OTP có hết hạn không
    if (token.isExpired()) {
        throw new RuntimeException("Mã OTP đã hết hạn");
    }

    // Tìm user theo email
    UserEntity user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

    // Cập nhật mật khẩu mới (đã mã hóa)
    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
    // Đánh dấu OTP đã được sử dụng
    // Đánh dấu token đã được sử dụng
    token.setIsUsed(true);
    token.setUsedAt(LocalDateTime.now());
    tokenRepository.save(token);
}
