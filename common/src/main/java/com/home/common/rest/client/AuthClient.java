package com.home.common.rest.client;

import org.springframework.stereotype.Component;

@Component
public class AuthClient {

        public Boolean validateToken(String token) {
//            return getWebClient("http://localhost:8081/auth")
//                    .post()
//                    .uri("/token")
//                    .bodyValue(new AuthRequest(username, password))
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .block();
            return null;
        }
}
