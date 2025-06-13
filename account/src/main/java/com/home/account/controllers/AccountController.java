package com.home.account.controllers;


import com.home.account.services.AccountService;
import com.home.common.entities.dtos.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountDTO create(AccountDTO accountDTO) {
        return accountService.create(accountDTO);
    }


    @GetMapping("/user/{userId}")
    public Stream<AccountDTO> getAccountsByUser(@PathVariable("userId") String userId) {
        return accountService.retrieveByUserId(userId);
    }

}
