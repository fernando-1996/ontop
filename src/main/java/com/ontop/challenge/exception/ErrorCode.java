package com.ontop.challenge.exception;

import lombok.Getter;

public enum ErrorCode {

    INVALID_BODY("amount and/or user_id must not be null"),
    INVALID_USER("user not found"),

    INVALID_AMOUNT("amount surpasses user's wallet balance"),
    GENERIC_ERROR("something bad happened");

    @Getter
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
