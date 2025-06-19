package com.home.banking.transaction.domain.ports.out;

import com.home.banking.transaction.domain.Transaction;
import com.home.banking.transaction.infrastructure.controllers.TransactionController;

import java.util.Optional;

public interface TransactionRepository {

    String create(Transaction transaction);

    Optional<Transaction> getById(String trxId);
}
