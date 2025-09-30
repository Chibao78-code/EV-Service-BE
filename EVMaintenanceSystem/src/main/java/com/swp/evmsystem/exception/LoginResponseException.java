package com.swp.evmsystem.exception;

public class LoginResponseException extends RuntimeException {
    public LoginResponseException(String message) {
        super(message);
    }

    public LoginResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginResponseException(Throwable cause) {
        super(cause);
    }
}
