package com.home.banking.transaction.repositories;

import com.home.common.entities.Transaction;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

    Optional<Transaction> getById(String id);
}
