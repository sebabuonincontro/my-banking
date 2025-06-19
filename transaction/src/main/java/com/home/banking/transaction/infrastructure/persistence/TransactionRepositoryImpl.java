package com.home.banking.transaction.infrastructure.persistence;

import com.home.banking.transaction.domain.Transaction;
import com.home.banking.transaction.domain.ports.out.TransactionRepository;
import com.home.banking.transaction.infrastructure.TransactionMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionRepositoryImpl implements TransactionRepository {

    private final JPATransactionRepository jpaTransactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionRepositoryImpl(JPATransactionRepository jpaTransactionRepository, TransactionMapper transactionMapper) {
        this.jpaTransactionRepository = jpaTransactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public String create(Transaction transaction) {
        var entity = transactionMapper.toEntity(transaction);
        var result = jpaTransactionRepository.save(entity);
        return result.getId();
    }

    @Override
    public Optional<Transaction> getById(String trxId) {
        return jpaTransactionRepository.findById(trxId).map(transactionMapper::fromEntity);
    }
}
