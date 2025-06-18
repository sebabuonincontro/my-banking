package com.home.banking.transaction.infrastructure.persistence;

import com.home.banking.transaction.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPATransactionRepository extends CrudRepository<TransactionEntity, String> {
}
