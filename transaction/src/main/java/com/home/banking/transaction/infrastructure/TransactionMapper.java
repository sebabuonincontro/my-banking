package com.home.banking.transaction.infrastructure;

import com.home.banking.transaction.domain.Transaction;
import com.home.banking.transaction.infrastructure.controllers.dtos.TransactionDTO;
import com.home.banking.transaction.infrastructure.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toDomain(TransactionDTO dto);

    TransactionDTO toDto(Transaction transaction);

    TransactionEntity toEntity(Transaction transaction);
}
