package com.home.banking.transaction.infrastructure.controllers.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TransactionDTO {

    private LocalDate createDate;
    private Long userId;
    private String accountSource;
    private String accountDestination;
    private BigDecimal amount;

}
