package com.app.simbongsa.exception;

import com.app.simbongsa.type.ErrorCode;

public class BoardNotFoundException extends RuntimeException{

    public BoardNotFoundException() {
        super(ErrorCode.BOARD_NOT_FOUNT.name());
    }

    public BoardNotFoundException(String message){
        super(message);
    }
}
