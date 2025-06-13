package com.home.account.controllers;

import com.home.account.services.AccountService;
import com.home.common.entities.dtos.AccountDTO;
import com.home.common.entities.dtos.TransactionDTO;
import com.home.common.entities.dtos.UserDTO;
import com.home.common.rest.client.TransactionClient;
import com.home.common.rest.client.UserClient;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class TransactionControllerTest {

    @Mock
    private TransactionClient transactionClient;
    @Mock
    private UserClient userClient;
    @Mock
    private AccountService accountService;

    @InjectMocks
    private TransactionController transactionController;


    public void persistNewTransaction() {
        val userId = "123";
        val accountSource = "111111";
        val accountDestination = "2222222";

        val trx = new TransactionDTO();
        trx.setId(1234567L);
        trx.setAmount(new BigDecimal(10000));
        trx.setCreateDate(LocalDate.now());
        trx.setUserId(userId);
        trx.setAccountSource(accountSource);
        trx.setAccountDestination(accountDestination);

        when(accountService.retrieveById(eq(accountSource))).thenReturn(Optional.of(
                AccountDTO.builder()
                        .withId(accountSource)
                        .withUserId(userId)
                        .withNumber("123456")
                        .withCreateDate(LocalDate.now())
                        .build()));
        when(accountService.retrieveById(eq(accountDestination))).thenReturn(Optional.of(
                AccountDTO.builder()
                        .withId(accountDestination)
                        .withUserId(userId)
                        .withNumber("123456")
                        .withCreateDate(LocalDate.now())
                        .build()));
        when(userClient.retrieveUserBy(anyString())).thenReturn(Optional.of(UserDTO.builder().withId(123L).build()));
        when(transactionClient.createTrx(any(TransactionDTO.class))).thenReturn(trx);

        val newTrx = transactionController.createTrx(trx);

        assertNotNull(newTrx);
        assertEquals(newTrx.getAccountSource(), accountSource);
        assertEquals(newTrx.getAccountDestination(), accountDestination);
        assertEquals(newTrx.getUserId(), userId);
        verify(accountService, times(1)).retrieveById(accountSource);
        verify(userClient, times(1)).retrieveUserBy(userId);
        verify(transactionClient, times(1)).createTrx(trx);
    }

}