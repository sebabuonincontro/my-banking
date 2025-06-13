package com.home.banking.transaction.configuration;

import com.home.common.configuration.CommonConfiguration;
import com.home.common.configuration.RestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@Import({CommonConfiguration.class, RestConfiguration.class})
public class TransactionConfiguration {

    @Bean(name = "transactionTaskExecutor")
    Executor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("transaction-task-");
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        return taskExecutor;
    }
}
