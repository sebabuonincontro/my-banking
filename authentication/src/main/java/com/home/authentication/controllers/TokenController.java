package com.home.authentication.controllers;

import com.home.common.rest.client.AuthClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TokenController {

    private AuthClient authClient;

    public Boolean validateToken() {
        return authClient.validateToken();
    }
}
