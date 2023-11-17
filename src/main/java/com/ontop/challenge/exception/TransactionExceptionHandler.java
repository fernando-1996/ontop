package com.ontop.challenge.exception;

import com.ontop.challenge.record.TransactionErrorRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<TransactionErrorRecord> handleTransactionException(TransactionException ex) {
        TransactionErrorRecord error = new TransactionErrorRecord(ex.getCode(), ex.getMessage());
        return switch (ex.getCode())  {
            case INVALID_USER -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            case INVALID_BODY, INVALID_AMOUNT -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TransactionErrorRecord(ErrorCode.GENERIC_ERROR, ErrorCode.GENERIC_ERROR.getMessage()));
        };
    }
}
