package com.home.account.configuration;

import com.home.common.configuration.CommonConfiguration;
import com.home.common.configuration.RestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CommonConfiguration.class, RestConfiguration.class})
public class AccountConfiguration {

}
