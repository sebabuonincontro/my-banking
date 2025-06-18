package com.home.banking.transaction.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record Transaction(String id,
                          Long userId,
                          String accountSource,
                          String accountDestination,
                          LocalDate createDate,
                          BigDecimal amount,
                          String currency) {


    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
