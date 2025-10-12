package com.swp.evmsystem.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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
    //giao dien va noi dung gui qua email kèm otp
    private String buildOTPEmailContent(String otpCode) {
        return """
            <!DOCTYPE html>
            <html lang="vi">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        background-color: #f4f4f4;
                        margin: 0;
                        padding: 0;
                    }
                    .container {
                        max-width: 600px;
                        margin: 40px auto;
                        background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%);
                        border-radius: 15px;
                        overflow: hidden;
                        box-shadow: 0 10px 40px rgba(0,0,0,0.1);
                    }
                    .header {
                        background-color: rgba(255,255,255,0.1);
                        padding: 30px;
                        text-align: center;
                    }
                    .header h1 {
                        color: white;
                        margin: 0;
                        font-size: 28px;
                        font-weight: 600;
                    }
                    .content {
                        background-color: white;
                        padding: 40px 30px;
                    }
                    .otp-box {
                        background: linear-gradient(135deg, #667eea 0%%, #764ba2 100%%);
                        border-radius: 10px;
                        padding: 25px;
                        text-align: center;
                        margin: 30px 0;
                    }
                    .otp-code {
                        font-size: 42px;
                        font-weight: bold;
                        letter-spacing: 8px;
                        color: white;
                        margin: 10px 0;
                        font-family: 'Courier New', monospace;
                    }
                    .otp-label {
                        color: rgba(255,255,255,0.9);
                        font-size: 14px;
                        margin-bottom: 10px;
                    }
                    .message {
                        color: #333;
                        line-height: 1.8;
                        margin: 20px 0;
                    }
                    .warning {
                        background-color: #fff3cd;
                        border-left: 4px solid #ffc107;
                        padding: 15px;
                        margin: 20px 0;
                        border-radius: 5px;
                    }
                    .warning p {
                        margin: 5px 0;
                        color: #856404;
                    }
                    .footer {
                        background-color: #f8f9fa;
                        padding: 20px;
                        text-align: center;
                        color: #6c757d;
                        font-size: 12px;
                    }
                    .icon {
                        font-size: 50px;
                        margin-bottom: 10px;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <div class="icon">🚗</div>
                        <h1>EV Service System</h1>
                    </div>
                    <div class="content">
                        <h2 style="color: #333; margin-top: 0;">Xin chào! 👋</h2>
                        <p class="message">
                            Bạn đã yêu cầu đặt lại mật khẩu cho tài khoản của mình. 
                            Vui lòng sử dụng mã OTP bên dưới để xác nhận:
                        </p>
                        
                        <div class="otp-box">
                            <div class="otp-label">MÃ OTP CỦA BẠN</div>
                            <div class="otp-code">%s</div>
                        </div>
                        
                        <div class="warning">
                            <p><strong>⚠️ Lưu ý quan trọng:</strong></p>
                            <p>• Mã OTP này chỉ có hiệu lực trong <strong>5 phút</strong></p>
                            <p>• Không chia sẻ mã này với bất kỳ ai</p>
                            <p>• Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này</p>
                        </div>
                        
                        <p class="message">
                            Nếu bạn gặp bất kỳ vấn đề gì, vui lòng liên hệ với đội ngũ hỗ trợ của chúng tôi.
                        </p>
                    </div>
                    <div class="footer">
                        <p>© 2025 EV Service System. All rights reserved.</p>
                        <p>Email này được gửi tự động, vui lòng không trả lời.</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(otpCode);
    }
}
