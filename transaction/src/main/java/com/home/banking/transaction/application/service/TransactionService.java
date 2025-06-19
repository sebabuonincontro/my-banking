package com.home.banking.transaction.application.service;

import com.home.banking.transaction.domain.Account;
import com.home.banking.transaction.domain.Transaction;
import com.home.banking.transaction.domain.User;
import com.home.banking.transaction.domain.ports.in.AccountUseCase;
import com.home.banking.transaction.domain.ports.in.TransactionUseCase;
import com.home.banking.transaction.domain.ports.in.UserUseCase;
import com.home.banking.transaction.domain.ports.out.TransactionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.home.banking.transaction.application.service.exceptions.TransactionNotFoundException;

@Service
public class TransactionService implements TransactionUseCase {

    private final AccountUseCase accountUseCase;
    private final TransactionRepository transactionRepository;
    private final UserUseCase userUseCase;
    private final Executor executor;

    public TransactionService(AccountUseCase accountUseCase, TransactionRepository transactionRepository, UserUseCase userUseCase, @Qualifier("transactionTaskExecutor") Executor executor) {
        this.accountUseCase = accountUseCase;
        this.transactionRepository = transactionRepository;
        this.userUseCase = userUseCase;
        this.executor = executor;
    }

    @Override
    public String create(Transaction transaction) {

        CompletableFuture<List<Account>> accountSourcesFuture = CompletableFuture.supplyAsync(() -> accountUseCase.getAccountsBy(transaction.accountSource()), executor);
        CompletableFuture<List<Account>> accountDestinationFuture = CompletableFuture.supplyAsync(() -> accountUseCase.getAccountsBy(transaction.accountDestination()) , executor);
        CompletableFuture<Optional<User>> userFuture = CompletableFuture.supplyAsync(() -> userUseCase.retrieveUserBy(transaction.userId()), executor);

        CompletableFuture.allOf(accountSourcesFuture, accountDestinationFuture, userFuture).join();

        var newTrx = Transaction.builder();
        accountSourcesFuture.getNow(List.of())
                .stream()
                .findFirst()
                .ifPresent(source -> newTrx.accountSource(source.number()));
        accountDestinationFuture.getNow(List.of())
                .stream()
                .findFirst()
                .ifPresent(destination -> newTrx.accountDestination(destination.number()));
        userFuture.getNow(Optional.empty())
                .ifPresent(user -> newTrx.userId(user.id()));

        newTrx.id(transaction.generateId());
        newTrx.amount(transaction.amount());
        newTrx.currency(transaction.currency());
        newTrx.createDate(LocalDate.now());
        return transactionRepository.create(newTrx.build());
    }

    @Override
    public List<Transaction> getAll() {
        return List.of();
    }

    @Override
    public Transaction getById(String id) {
        return transactionRepository.getById(id)
                .orElseThrow(TransactionNotFoundException::new);
    }
}
