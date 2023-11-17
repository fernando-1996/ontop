package com.ontop.challenge.repository;

import com.ontop.challenge.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    @Query("SELECT SUM(COALESCE(amount, 0)) FROM Transaction WHERE user.id = :userId")
    BigDecimal getUserBalance(Long userId);
}
