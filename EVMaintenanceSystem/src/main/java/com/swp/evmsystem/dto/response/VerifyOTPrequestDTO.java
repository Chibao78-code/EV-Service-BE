package com.swp.evmsystem.dto.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class VerifyOTPrequestDTO {

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "OTP must not be blank")
    @Size(min = 4, max = 8, message = "OTP should be between 4 and 8 characters")
    private String otp;


    public VerifyOTPrequestDTO() {
    }
    public VerifyOTPrequestDTO(String email, String otp) { // Constructor to initialize email and otp
        this.email = email;
        this.otp = otp;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }
    @Override
    public String toString() {
        return "VerifyOTPrequestDTO{" +
                "email='" + email + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Check for null and class type

        VerifyOTPrequestDTO that = (VerifyOTPrequestDTO) o; // Cast to the correct type
        return Objects.equals(email, that.email) && // Compare relevant fields
               Objects.equals(otp, that.otp); // Compare relevant fields
    }
}