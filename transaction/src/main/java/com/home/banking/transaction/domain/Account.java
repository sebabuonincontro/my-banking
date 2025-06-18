package com.home.banking.transaction.domain;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record Account(Long id,
                      Long userId,
                      String number,
                      LocalDate createDate) {
}
