package com.home.common.rest.client;

import com.home.common.entities.dtos.TransactionDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class TransactionClient {

    private final CommonClient commonClient;

    public TransactionDTO createTrx(TransactionDTO dto) {
        return commonClient.getWebClient("http://localhost:8085/transaction")
                .post()
                .body(Mono.just(dto), TransactionDTO.class)
                .retrieve()
                .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), clientResponse -> Mono.empty())
                .bodyToMono(TransactionDTO.class)
                .block();
    }
}
