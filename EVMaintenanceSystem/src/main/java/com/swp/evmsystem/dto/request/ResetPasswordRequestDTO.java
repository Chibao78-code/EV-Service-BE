package com.swp.evmsystem.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class ResetPasswordRequestDTO {

    @NotBlank(message = "Token must not be blank")
    private String token;

    
}