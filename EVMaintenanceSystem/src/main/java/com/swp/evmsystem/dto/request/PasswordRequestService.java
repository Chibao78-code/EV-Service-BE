package com.swp.evmsystem.dto.request;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PasswordRequestService implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    @NotBlank(message = "Current password is required")
    private String currentPassword;

    @NotBlank(message = "New password is required")
    @Size(min = 8, max = 64, message = "New password must be between 8 and 64 characters")
    private String newPassword;

    @NotBlank(message = "Confirm password is required")
    @JsonIgnore
    private String confirmPassword;
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}