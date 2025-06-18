package com.home.account.repositories;

import com.home.common.entities.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.stream.Stream;

public interface AccountRepository extends CrudRepository<Account, String> {

    Stream<Account> getAll();
}
