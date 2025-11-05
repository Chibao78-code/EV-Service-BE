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