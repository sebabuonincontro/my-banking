package com.home.account.controllers;

import com.home.account.exceptions.AccountException;
import com.home.account.services.AccountService;
import com.home.common.entities.dtos.AccountDTO;
import com.home.common.entities.dtos.TransactionDTO;
import com.home.common.entities.dtos.UserDTO;
import com.home.common.rest.client.TransactionClient;
import com.home.common.rest.client.UserClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
@Slf4j
public class TransactionController {

    private final AccountService accountService;
    private final UserClient userClient;
    private final TransactionClient transactionClient;

    @PostMapping("/transaction")
    public TransactionDTO createTrx(TransactionDTO trw) {
        //retrieve account detail
        var accountSource = accountService.retrieveById(trw.getAccountSource());
        var accountDestination = accountService.retrieveById(trw.getAccountDestination());
        var user = userClient.retrieveUserBy(trw.getUserId());

        accountSource.ifPresent(source -> trw.setAccountDestination(source.getId()));
        accountDestination.ifPresent(destination -> trw.setAccountDestination(destination.getId()));
        user.ifPresent(u -> trw.setUserId(u.getId().toString()));

        return transactionClient.createTrx(trw);
    }
}
