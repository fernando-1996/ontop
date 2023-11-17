package com.ontop.challenge.mapper;

import com.ontop.challenge.model.Transaction;
import com.ontop.challenge.record.TransactionRecord;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TransactionMapper {

    @Mapping(source = "id", target = "walletTransactionId")
    @Mapping(source = "user.id", target = "userId")
    TransactionRecord toTransactionRecord(Transaction transaction);

    @Mapping(source = "walletTransactionId", target = "id")
    @Mapping(source = "userId", target = "user.id")
    Transaction toTransaction(TransactionRecord transactionRecord);
}
