package com.ontop.challenge.record;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRecord(
        @JsonProperty("wallet_transaction_id")
        Long walletTransactionId,
        @JsonProperty("user_id")
        Long userId,
        BigDecimal amount) {
}
