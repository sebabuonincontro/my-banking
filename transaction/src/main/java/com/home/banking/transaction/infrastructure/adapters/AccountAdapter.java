package com.home.banking.transaction.infrastructure.adapters;

import com.home.banking.transaction.domain.Account;
import com.home.banking.transaction.domain.ports.in.AccountUseCase;
import com.home.common.rest.client.AccountClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountAdapter implements AccountUseCase {

    private final AccountClient accountClient;
    private final AccountMapper accountMapper;

    public AccountAdapter(AccountClient accountClient, AccountMapper accountMapper) {
        this.accountClient = accountClient;
        this.accountMapper = accountMapper;
    }

    @Override
    public List<Account> getAccountsBy(String accountNumber) {
        var listAccount = this.accountClient.getAccountsByNumber(accountNumber);
        return listAccount.stream().map(accountMapper::toDomain).toList();
    }
}
