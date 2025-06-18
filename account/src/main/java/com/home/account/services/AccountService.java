package com.home.account.services;

import com.home.account.mappers.AccountMapper;
import com.home.account.repositories.AccountRepository;
import com.home.common.entities.Account;
import com.home.common.entities.dtos.AccountDTO;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
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

    public List<AccountDTO> retrieveByUserId(String userId) {
        return accountRepository.getAll()
                .filter(account -> account.getUserId().equalsIgnoreCase(userId))
                .map(accountMapper::toDto)
                .toList();
    }

    public Optional<AccountDTO> retrieveById(String accountId) {
        return accountRepository.findById(accountId).map(accountMapper::toDto);
    }
}
