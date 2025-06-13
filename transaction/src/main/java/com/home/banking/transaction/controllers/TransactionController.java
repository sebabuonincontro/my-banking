package com.home.banking.transaction.controllers;

import com.home.banking.transaction.exceptions.TransactionNotFoundException;
import com.home.banking.transaction.mappers.TransactionMapper;
import com.home.banking.transaction.services.TransactionService;
import com.home.common.entities.dtos.TransactionDTO;
import com.home.common.entities.dtos.TransactionIdDto;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @PostMapping()
    public TransactionIdDto createTrx(@NotBlank TransactionDTO transactionDTO) {
        val entity = transactionMapper.toEntity(transactionDTO);
        entity.setId(UUID.randomUUID().toString());
        transactionService.createTrx(entity);
        return TransactionIdDto.builder()
            .withId(entity.getId())
            .build();
    }

    @GetMapping("/{trxId}")
    public TransactionDTO getById(@PathVariable("trxId") String trxId) {
        return transactionService.getById(trxId)
            .map(transactionMapper::toDto)
            .orElseThrow(TransactionNotFoundException::new);
    }
}
