package com.ontop.challenge.service;

import com.ontop.challenge.exception.ErrorCode;
import com.ontop.challenge.exception.TransactionException;
import com.ontop.challenge.mapper.TransactionMapper;
import com.ontop.challenge.model.Transaction;
import com.ontop.challenge.model.User;
import com.ontop.challenge.record.TransactionRecord;
import com.ontop.challenge.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Transactional
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final UserService userService;

    public TransactionRecord createTransaction(TransactionRecord transactionRecord) {
        if (transactionRecord.amount() == null || transactionRecord.amount().equals(BigDecimal.ZERO)
                || transactionRecord.userId() == null || transactionRecord.userId() == 0)
            throw new TransactionException(ErrorCode.INVALID_BODY);

        User user = userService.findUserById(transactionRecord.userId());
        if (user == null)
            throw new TransactionException(ErrorCode.INVALID_USER);

        Transaction transaction = transactionMapper.toTransaction(transactionRecord);
        if (transactionRecord.amount().compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal userBalance = transactionRepository.getUserBalance(transactionRecord.userId());
            if (userBalance == null || userBalance.compareTo(transactionRecord.amount().abs()) < 0)
                throw new TransactionException(ErrorCode.INVALID_AMOUNT);
        }

        transaction = transactionRepository.save(transaction);
        return transactionMapper.toTransactionRecord(transaction);
    }
}
