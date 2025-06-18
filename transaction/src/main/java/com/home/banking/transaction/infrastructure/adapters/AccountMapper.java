package com.home.banking.transaction.infrastructure.adapters;

import com.home.banking.transaction.domain.Account;
import com.home.common.entities.dtos.AccountDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toDomain(AccountDTO accountDTO);
}
