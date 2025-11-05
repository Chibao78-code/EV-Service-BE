package com.swp.evmsystem.service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendOTPEmail(String toEmail, String otpCode) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject("🔐 Mã OTP Đặt Lại Mật Khẩu - EV Service");

        String htmlContent = buildOTPEmailContent(otpCode);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }


    
}