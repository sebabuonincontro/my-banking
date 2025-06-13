package com.home.banking.transaction.services;

import com.home.banking.transaction.repositories.TransactionRepository;
import com.home.common.entities.Transaction;
import com.home.common.entities.dtos.AccountDTO;
import com.home.common.entities.dtos.UserDTO;
import com.home.common.rest.client.AccountClient;
import com.home.common.rest.client.UserClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class TransactionService {

    private final AccountClient accountClient;
    private final UserClient userClient;
    private final TransactionRepository transactionRepository;

    private final Executor executor;

    public TransactionService(AccountClient accountClient, UserClient userClient, TransactionRepository transactionRepository,
                              @Qualifier("transactionTaskExecutor") Executor transactionTaskExecutor) {
        this.accountClient = accountClient;
        this.userClient = userClient;
        this.transactionRepository = transactionRepository;
        this.executor = transactionTaskExecutor;
    }

    @Async("transactionTaskExecutor")
    public void createTrx(Transaction trw) {
        Transaction newTrx = new Transaction();

        CompletableFuture<List<AccountDTO>> accountSourcesFuture = CompletableFuture.supplyAsync(() -> accountClient.getAccountsBy(trw.getAccountSource()), executor);
        CompletableFuture<List<AccountDTO>> accountDestinationFuture = CompletableFuture.supplyAsync(() -> accountClient.getAccountsBy(trw.getAccountDestination()), executor);
        CompletableFuture<Optional<UserDTO>> userFuture = CompletableFuture.supplyAsync(() -> userClient.retrieveUserBy(trw.getUserId()), executor);

        CompletableFuture.allOf(accountSourcesFuture, accountDestinationFuture, userFuture).join();

        accountSourcesFuture.getNow(List.of())
                .stream()
                .findFirst()
                .ifPresent(source -> newTrx.setAccountSource(source.getId()));
        accountDestinationFuture.getNow(List.of())
                .stream()
                .findFirst()
                .ifPresent(destination -> newTrx.setAccountDestination(destination.getId()));
        userFuture.getNow(Optional.empty())
                .ifPresent(u -> newTrx.setUserId(u.getId().toString()));

        newTrx.setId(trw.getId());
        newTrx.setAmount(trw.getAmount());
        newTrx.setCreateDate(LocalDate.now());
        transactionRepository.save(newTrx);
    }

    @Transactional(readOnly = true)
    public Optional<Transaction> getById(String trxId) {
        return transactionRepository.getById(trxId);
    }

}
