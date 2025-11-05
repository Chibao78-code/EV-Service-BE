package com.swp.evmsystem.service;

public class PasswordResetService {
    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${otp.expiration.minutes:5}")
    private int otpExpirationMinutes;

    @Value("${otp.length:6}")
    private int otpLength;
    
}
