package com.swp.evmsystem.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ResetPasswordRequestDTO {

    @NotBlank(message = "Token must not be blank")
    private String token;

    @NotBlank(message = "New password must not be blank") // Ensure password is not blank
    @Size(min = 8, message = "Password must be at least 8 characters long") // Example constraint
    private String newPassword;

    public ResetPasswordRequestDTO() {
    }
    public ResetPasswordRequestDTO(String token, String newPassword) {
        this.token = token; // Initialize token
        this.newPassword = newPassword; // Initialize new password
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    @Override
    public String toString() {
        return "ResetPasswordRequestDTO{" +
                "token='" + token + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResetPasswordRequestDTO that = (ResetPasswordRequestDTO) o;
        return Objects.equals(token, that.token) &&
               Objects.equals(newPassword, that.newPassword);
        
    }
}