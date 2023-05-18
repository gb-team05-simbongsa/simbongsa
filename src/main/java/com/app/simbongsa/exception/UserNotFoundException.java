package com.app.simbongsa.exception;


import com.app.simbongsa.type.ErrorCode;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND.name());
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
