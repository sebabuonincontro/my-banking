package com.home.account.services;

import com.google.common.collect.Lists;
import com.home.account.exceptions.AccountException;
import com.home.account.mappers.AccountMapper;
import com.home.account.repositories.AccountRepository;
import com.home.common.entities.Account;
import com.home.common.entities.dtos.AccountDTO;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.accountMapper = AccountMapper.INSTANCE;
    }

    public AccountDTO create(AccountDTO accountDTO) {
        Account account = Optional.ofNullable(accountDTO)
                .map(accountMapper::mapTo)
                .orElseThrow();

        return Optional.of(accountRepository.save(account))
                .map(accountMapper::toDto).orElseThrow();
    }

    public Stream<AccountDTO> retrieveByUserId(String userId) {
        return Stream.of(accountRepository.findAll())
                .map(Lists::newArrayList)
                .flatMap(Collection::parallelStream)
                .filter(account -> account.getUserId().equalsIgnoreCase(userId))
                .map(accountMapper::toDto);
    }

    public Optional<AccountDTO> retrieveById(String accountId) {
        return accountRepository.findById(accountId).map(accountMapper::toDto);
    }
}
