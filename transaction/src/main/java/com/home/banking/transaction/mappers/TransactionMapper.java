package com.home.banking.transaction.mappers;

import com.home.common.entities.Transaction;
import com.home.common.entities.dtos.TransactionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toEntity(TransactionDTO dto);

    TransactionDTO toDto(Transaction transaction);
}
