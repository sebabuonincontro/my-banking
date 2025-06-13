package com.home.account;

import com.home.account.controllers.AccountController;
import com.home.account.controllers.TransactionController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WebApplicationTest {

    @Autowired
    private TransactionController transactionController;
    @Autowired
    private AccountController accountController;

    @Test
    void contextLoads() {
        Assertions.assertThat(transactionController).isNotNull();
        Assertions.assertThat(accountController).isNotNull();
    }
}
