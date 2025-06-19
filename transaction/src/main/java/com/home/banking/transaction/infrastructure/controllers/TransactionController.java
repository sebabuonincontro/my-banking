package com.home.banking.transaction.infrastructure.controllers;

import com.home.banking.transaction.application.service.TransactionService;
import com.home.banking.transaction.domain.Transaction;
import com.home.banking.transaction.infrastructure.TransactionMapper;
import com.home.banking.transaction.infrastructure.controllers.dtos.TransactionDTO;
import com.home.banking.transaction.infrastructure.controllers.dtos.TransactionIdDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @PostMapping()
    public TransactionIdDto createTrx(@NotBlank TransactionDTO transactionDTO) {
        Transaction transactionDomain = transactionMapper.toDomain(transactionDTO);
        var id = transactionService.create(transactionDomain);
        return TransactionIdDto.builder()
            .withId(id)
            .build();
    }

    @GetMapping("/{trxId}")
    public TransactionDTO getById(@PathVariable("trxId") String trxId) {
        var trx = transactionService.getById(trxId);
        return transactionMapper.toDto(trx);
    }
}
