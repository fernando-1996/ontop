package com.ontop.challenge.exception;

import lombok.Getter;

@Getter
public class TransactionException extends RuntimeException {

    private ErrorCode code;
    public TransactionException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode;
    }
    public TransactionException(String message) {
        super(message);
    }
}
