package com.home.common.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions", schema = "home_banking")
public class Transaction {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDate createDate;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "ACCOUNT_SOURCE", nullable = false)
    private String accountSource;

    @Column(name = "ACCOUNT_DESTINATION", nullable = false)
    private String accountDestination;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
}
