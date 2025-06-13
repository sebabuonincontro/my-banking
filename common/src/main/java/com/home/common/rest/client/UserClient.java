package com.home.common.rest.client;

import com.home.common.entities.dtos.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserClient {

    private final CommonClient commonClient;

    public Optional<UserDTO> retrieveUserBy(String userId) {
        return commonClient.getWebClient("http://localhost:8080/users/" + userId)
            .get()
            .retrieve()
            .bodyToMono(UserDTO.class)
            .onErrorResume(WebClientResponseException.NotFound.class, notFound -> Mono.empty())
            .blockOptional();
    }

    public UserDTO save(UserDTO userDTO) {
        commonClient.getWebClient("http://localhost:8080/users")
            .post()
            .body(Mono.just(userDTO), UserDTO.class)
            .retrieve()
            .onStatus(httpStatus -> !httpStatus.is2xxSuccessful(), clientResponse -> Mono.empty())
            .bodyToMono(UserDTO.class)
            .block();
        return userDTO;
    }

}
