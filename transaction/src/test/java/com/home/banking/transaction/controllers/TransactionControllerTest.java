package com.home.banking.transaction.controllers;

import com.home.banking.transaction.services.TransactionService;
import com.home.common.entities.dtos.AccountDTO;
import com.home.common.entities.dtos.TransactionDTO;
import com.home.common.entities.dtos.UserDTO;
import com.home.common.rest.client.AccountClient;
import com.home.common.rest.client.UserClient;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class TransactionControllerTest {

    @MockBean
    private UserClient userClient;
    @MockBean
    private AccountClient accountClient;

    @Autowired
    private TransactionController transactionController;

    @Test
    @Transactional
    public void persistNewTransaction() {
        val userId = "123";
        val accountSource = "111111";
        val accountDestination = "2222222";

        val trx = new TransactionDTO();
        trx.setAmount(new BigDecimal(10000));
        trx.setCreateDate(LocalDate.now());
        trx.setUserId(userId);
        trx.setAccountSource(accountSource);
        trx.setAccountDestination(accountDestination);

        when(accountClient.getAccountsBy(eq(accountSource))).thenReturn(List.of(
                AccountDTO.builder()
                        .withId(accountSource)
                        .withUserId(userId)
                        .withNumber("123456")
                        .withCreateDate(LocalDate.now())
                        .build()));
        when(accountClient.getAccountsBy(eq(accountDestination))).thenReturn(List.of(
                AccountDTO.builder()
                        .withId(accountDestination)
                        .withUserId(userId)
                        .withNumber("123456")
                        .withCreateDate(LocalDate.now())
                        .build()));
        when(userClient.retrieveUserBy(anyString())).thenReturn(Optional.of(UserDTO.builder().withId(123L).build()));

        val newTrx = transactionController.createTrx(trx);

        assertNotNull(newTrx);
        assertFalse(newTrx.getId().isEmpty());
        verify(accountClient, times(1)).getAccountsBy(accountSource);
        verify(accountClient, times(1)).getAccountsBy(accountDestination);
        verify(userClient, times(1)).retrieveUserBy(userId);

        var provided = transactionController.getById(newTrx.getId());

        assertEquals(provided.getAccountSource(), trx.getAccountSource());
        assertEquals(provided.getAccountDestination(), trx.getAccountDestination());
    }

}