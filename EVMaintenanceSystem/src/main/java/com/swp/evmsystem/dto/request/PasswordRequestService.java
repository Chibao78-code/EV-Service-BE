java
package com.swp.evmsystem.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ForgotPasswordRequestDTO {

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be valid")
    private String email;

    public ForgotPasswordRequestDTO() {
    }

    public ForgotPasswordRequestDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ForgotPasswordRequestDTO{" +
                "email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForgotPasswordRequestDTO that = (ForgotPasswordRequestDTO) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}