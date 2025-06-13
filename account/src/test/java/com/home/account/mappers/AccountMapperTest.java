package com.home.account.mappers;

import com.home.common.entities.Account;
import com.home.common.entities.dtos.AccountDTO;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountMapperTest {


    public void testMapperToDto() {
        var account = Account.builder()
                .id("id")
                .createDate(LocalDate.now())
                .number("1234556")
                .userId("userId")
                .build();

        var dto = AccountMapper.INSTANCE.toDto(account);

        assertThat(account.getId()).isEqualTo(dto.getId());
        assertThat(account.getUserId()).isEqualTo(dto.getUserId());
        assertThat(account.getCreateDate()).isEqualTo(dto.getCreateDate());
        assertThat(account.getNumber()).isEqualTo(dto.getNumber());
    }

    @Test
    public void testMapperToEntity() {
        var dto = AccountDTO.builder()
                .withId("id")
                .withCreateDate(LocalDate.now())
                .withNumber("1234556")
                .withUserId("userId")
                .build();

        var account = AccountMapper.INSTANCE.mapTo(dto);

        assertThat(account.getId()).isEqualTo(dto.getId());
        assertThat(account.getUserId()).isEqualTo(dto.getUserId());
        assertThat(account.getCreateDate()).isEqualTo(dto.getCreateDate());
        assertThat(account.getNumber()).isEqualTo(dto.getNumber());
    }
}
