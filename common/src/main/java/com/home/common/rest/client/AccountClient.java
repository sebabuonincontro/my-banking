package com.home.common.rest.client;

import com.home.common.entities.dtos.AccountDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AccountClient {

    private final CommonClient commonClient;

    public List<AccountDTO> getAccountsByUser(String userId) {
        return commonClient.getWebClient("http://localhost:8082/account")
                .get()
                .uri("/user/{userId}", userId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AccountDTO>>() {})
                .block();
    }

    public List<AccountDTO> getAccountsByNumber(String accountNumber) {
        return commonClient.getWebClient("http://localhost:8082/account")
                .get()
                .uri("/{number}", accountNumber)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AccountDTO>>() {})
                .block();
    }
}
