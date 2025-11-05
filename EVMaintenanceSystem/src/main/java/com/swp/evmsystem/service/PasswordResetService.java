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
}