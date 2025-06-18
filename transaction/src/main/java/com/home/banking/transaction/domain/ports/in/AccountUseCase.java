package com.home.banking.transaction.domain.ports.in;

import com.home.banking.transaction.domain.Account;

import java.util.List;

public interface AccountUseCase {
    List<Account> getAccountsBy(String accountNumber);
}
