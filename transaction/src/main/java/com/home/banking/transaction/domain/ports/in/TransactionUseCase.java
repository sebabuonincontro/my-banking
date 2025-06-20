package com.home.banking.transaction.domain.ports.in;

import com.home.banking.transaction.domain.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionUseCase {

    String create(Transaction transaction);

    List<Transaction> getAll();

    Transaction getById(String id);
}
