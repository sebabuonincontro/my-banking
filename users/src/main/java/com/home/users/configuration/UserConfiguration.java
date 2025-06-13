package com.home.users.configuration;

import com.home.common.configuration.CommonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import(CommonConfiguration.class)
public class UserConfiguration {

}
