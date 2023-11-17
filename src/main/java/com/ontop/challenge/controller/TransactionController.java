package com.ontop.challenge.controller;

import com.ontop.challenge.record.TransactionRecord;
import com.ontop.challenge.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/wallets/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionRecord createTransaction(@RequestBody TransactionRecord transactionRecord) {
        return transactionService.createTransaction(transactionRecord);
    }

}
