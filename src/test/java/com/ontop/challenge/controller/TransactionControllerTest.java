package com.ontop.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontop.challenge.exception.ErrorCode;
import com.ontop.challenge.exception.TransactionException;
import com.ontop.challenge.record.TransactionRecord;
import com.ontop.challenge.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String TRANSACTION_PATH = "/wallets/transactions";

    @Test
    public void shouldReturnOk() throws Exception {
        TransactionRecord transaction = new TransactionRecord(null, 1000L, new BigDecimal("500"));

        String json = mapper.writeValueAsString(transaction);

        this.mockMvc
                .perform(post(TRANSACTION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        TransactionRecord transaction = new TransactionRecord(null, 404L, new BigDecimal("500"));

        String json = mapper.writeValueAsString(transaction);

        when(transactionService.createTransaction(transaction)).thenThrow(new TransactionException(ErrorCode.INVALID_USER));

        this.mockMvc
                .perform(post(TRANSACTION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnBadRequest() throws Exception {
        TransactionRecord transaction = new TransactionRecord(null, 400L, new BigDecimal("500"));

        String json = mapper.writeValueAsString(transaction);

        when(transactionService.createTransaction(transaction)).thenThrow(new TransactionException(ErrorCode.INVALID_BODY));

        this.mockMvc
                .perform(post(TRANSACTION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnGenericError() throws Exception {
        TransactionRecord transaction = new TransactionRecord(null, 500L, new BigDecimal("500"));

        String json = mapper.writeValueAsString(transaction);

        when(transactionService.createTransaction(transaction)).thenThrow(new TransactionException(ErrorCode.GENERIC_ERROR));

        this.mockMvc
                .perform(post(TRANSACTION_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isInternalServerError());
    }
}
