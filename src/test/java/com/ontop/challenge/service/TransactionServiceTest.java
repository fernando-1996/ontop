package com.ontop.challenge.service;

import com.ontop.challenge.exception.TransactionException;
import com.ontop.challenge.mapper.TransactionMapper;
import com.ontop.challenge.model.Transaction;
import com.ontop.challenge.model.User;
import com.ontop.challenge.record.TransactionRecord;
import com.ontop.challenge.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void shouldCreateTransaction() {
        TransactionRecord transaction = new TransactionRecord(null, 1000L, new BigDecimal("500"));
        TransactionRecord savedTransaction = new TransactionRecord(1L, 1000L, new BigDecimal("500"));

        User mockUser = getMockUser();
        when(userService.findUserById(any())).thenReturn(mockUser);
        when(transactionRepository.save(any())).thenReturn(getMockTransaction(mockUser));
        when(transactionMapper.toTransactionRecord(any())).thenReturn(savedTransaction);

        TransactionRecord result = transactionService.createTransaction(transaction);
        Assertions.assertNotNull(result.walletTransactionId());
    }

    @Test
    public void shouldThrowInvalidBody() {
        TransactionRecord transaction = new TransactionRecord(null, 1000L, null);

        Assertions.assertThrows(TransactionException.class, () -> transactionService.createTransaction(transaction));
    }

    @Test
    public void shouldThrowInvalidUser() {
        TransactionRecord transaction = new TransactionRecord(null, 404L, new BigDecimal("500"));

        when(userService.findUserById(any())).thenReturn(null);
        Assertions.assertThrows(TransactionException.class, () -> transactionService.createTransaction(transaction));
    }

    @Test
    public void shouldThrowInvalidAmount() {
        TransactionRecord transaction = new TransactionRecord(null, 1000L, new BigDecimal("-500"));

        User mockUser = getMockUser();
        when(userService.findUserById(any())).thenReturn(mockUser);

        Assertions.assertThrows(TransactionException.class, () -> transactionService.createTransaction(transaction));
    }

    private Transaction getMockTransaction(User user) {
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(new BigDecimal("500"));
        transaction.setId(1L);
        transaction.setDateCreated(LocalDateTime.now());
        transaction.setDateUpdated(LocalDateTime.now());
        return transaction;
    }

    private User getMockUser() {
        User user = new User();
        user.setId(1000L);
        user.setDateCreated(LocalDateTime.now());
        user.setDateUpdated(LocalDateTime.now());
        user.setName("Test");
        return user;
    }
}
