package com.ontop.challenge.record;

import com.ontop.challenge.exception.ErrorCode;

public record TransactionErrorRecord(
        ErrorCode code,
        String message) {
}
