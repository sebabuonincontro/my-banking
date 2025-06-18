package com.home.banking.transaction.domain.ports.out;

import com.home.banking.transaction.domain.Transaction;

public interface TransactionRepository {

    String create(Transaction transaction);
}
