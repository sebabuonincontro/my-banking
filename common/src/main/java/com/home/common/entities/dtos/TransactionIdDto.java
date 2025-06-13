package com.home.common.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@Builder(setterPrefix = "with")
@EqualsAndHashCode
public class TransactionIdDto {

    private String id;

}
