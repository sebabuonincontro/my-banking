package com.home.common.entities.dtos;

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

    private String id;
    private LocalDate createDate;
    private String userId;
    private String accountSource;
    private String accountDestination;
    private BigDecimal amount;

}
