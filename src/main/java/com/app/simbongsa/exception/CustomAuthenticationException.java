package com.app.simbongsa.exception;


import com.app.simbongsa.type.ErrorCode;

public class CustomAuthenticationException extends RuntimeException{
    public CustomAuthenticationException() {
        super(ErrorCode.AUTHENTICATION_FAILED.getMessage());
    }

    public CustomAuthenticationException(String message) {
        super(message);
    }
}
