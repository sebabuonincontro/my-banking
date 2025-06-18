package com.home.account;

import com.home.account.controllers.AccountController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WebApplicationTest {

    @Autowired
    private AccountController accountController;

    @Test
    public void contextLoads() {
        Assertions.assertThat(accountController).isNotNull();
    }
}
