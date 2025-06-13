package com.home.common.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts", schema = "home_banking")
public class Account {

    @Id
    @Column(name = "ID", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;

}
