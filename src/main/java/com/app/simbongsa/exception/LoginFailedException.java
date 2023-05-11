package com.app.simbongsa.exception;


import com.app.simbongsa.type.ErrorCode;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException() {
        super(ErrorCode.LOGIN_FAILED.getMessage());
    }

    public LoginFailedException(String message) {
        super(message);
    }
}
