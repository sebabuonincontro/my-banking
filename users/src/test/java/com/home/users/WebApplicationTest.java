package com.home.users;

import com.home.users.controllers.UserController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WebApplicationTest {

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() {
        Assertions.assertThat(userController).isNotNull();
    }
}
