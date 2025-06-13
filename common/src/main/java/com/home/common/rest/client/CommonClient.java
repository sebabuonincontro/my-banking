package com.home.common.rest.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public final class CommonClient {

    public WebClient getWebClient(String url) {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
