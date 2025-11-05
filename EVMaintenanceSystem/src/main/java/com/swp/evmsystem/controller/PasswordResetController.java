package com.swp.evmsystem.controller;

@RestController
@RequestMapping("/api/auth/password")
@RequiredArgsConstructor
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    //gui otp
    @PostMapping("/forgot")
    public ResponseEntity<MessageResponseDTO> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequestDTO request) {
        try {
            passwordResetService.sendOTPToEmail(request.getEmail());

            return ResponseEntity.ok(MessageResponseDTO.builder()
                    .success(true)
                    .message("Mã OTP đã được gửi đến email của bạn. Vui lòng kiểm tra hộp thư.")
                    .build());

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(MessageResponseDTO.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build());

        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MessageResponseDTO.builder()
                            .success(false)
                            .message("Không thể gửi email. Vui lòng thử lại sau.")
                            .build());
        }
    }

    //xac minh otp
    @PostMapping("/verify-otp")
    public ResponseEntity<MessageResponseDTO> verifyOTP(
            @Valid @RequestBody VerifyOTPRequestDTO request) {
        try {
            boolean isValid = passwordResetService.verifyOTP(
                    request.getEmail(),
                    request.getOtpCode());

            if (isValid) {
                return ResponseEntity.ok(MessageResponseDTO.builder()
                        .success(true)
                        .message("Mã OTP hợp lệ. Bạn có thể đặt lại mật khẩu.")
                        .build());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(MessageResponseDTO.builder()
                                .success(false)
                                .message("Mã OTP không hợp lệ hoặc đã hết hạn.")
                                .build());
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MessageResponseDTO.builder()
                            .success(false)
                            .message("Đã xảy ra lỗi. Vui lòng thử lại.")
                            .build());
        }
    }
}