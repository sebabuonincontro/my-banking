package com.home.account.mappers;

import com.home.common.entities.Account;
import com.home.common.entities.dtos.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO toDto(Account account);

    Account mapTo(AccountDTO accountDTO);
}
