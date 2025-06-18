package com.home.banking.transaction.controllers;

import com.home.banking.transaction.domain.Account;
import com.home.banking.transaction.domain.User;
import com.home.banking.transaction.domain.ports.in.AccountUseCase;
import com.home.banking.transaction.domain.ports.in.UserUseCase;
import com.home.banking.transaction.infrastructure.controllers.TransactionController;
import com.home.banking.transaction.infrastructure.controllers.dtos.TransactionDTO;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TransactionControllerTest {

    @MockBean
    private UserUseCase userUseCase;
    @MockBean
    private AccountUseCase accountUseCase;

    @Autowired
    private TransactionController transactionController;

    @Test
    @Transactional
    public void persistNewTransaction() {
        val userId = 123L;
        val accountSource = "111111";
        val accountDestination = "2222222";

        val trx = new TransactionDTO();
        trx.setAmount(new BigDecimal(10000));
        trx.setCreateDate(LocalDate.now());
        trx.setUserId(userId);
        trx.setAccountSource(accountSource);
        trx.setAccountDestination(accountDestination);

        when(accountUseCase.getAccountsBy(eq(accountSource))).thenReturn(List.of(
                Account.builder()
                        .id(1L)
                        .userId(userId)
                        .number(accountSource)
                        .createDate(LocalDate.now())
                        .build()));
        when(accountUseCase.getAccountsBy(eq(accountDestination))).thenReturn(List.of(
                Account.builder()
                        .id(2L)
                        .userId(userId)
                        .number(accountDestination)
                        .createDate(LocalDate.now())
                        .build()));
        when(userUseCase.retrieveUserBy(userId)).thenReturn(Optional.of(User.builder().id(userId).build()));

        val newTrx = transactionController.createTrx(trx);

        assertNotNull(newTrx);
        assertFalse(newTrx.getId().isEmpty());
        verify(accountUseCase, times(1)).getAccountsBy(accountSource);
        verify(accountUseCase, times(1)).getAccountsBy(accountDestination);
        verify(userUseCase, times(1)).retrieveUserBy(userId);

//        var provided = transactionController.getById(newTrx.getId());
//
//        assertEquals(provided.getAccountSource(), trx.getAccountSource());
//        assertEquals(provided.getAccountDestination(), trx.getAccountDestination());
    }

}