package com.home.users.services;

import com.home.common.entities.dtos.UserDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@Getter
@RequiredArgsConstructor
public class UserEventManager {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;
    private final UserService userService;

    @Value("${spring.kafka.topic.name}")
    private String userTopic;

    public void sendMessage(UserEvent userEvent) {
        Optional.ofNullable(userEvent)
                .ifPresent(event -> {
                    log.info(String.format("Sending User to Kafka: %s", userEvent));
                    kafkaTemplate.send(userTopic, userEvent);
                    kafkaTemplate.flush();
                });
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload UserEvent userEvent,
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition,
                        @Header(KafkaHeaders.OFFSET) Long offset) {
        log.info("Kafka message with user {}, from {} topic, " +
                "{} partition, and {} offset", userEvent.getUser(), topic, partition, offset);
        Optional.of(userEvent.getUser())
                .ifPresent(dto -> {
                    log.info(String.format("Saving User: %s", dto));
                    userService.create(dto);
                });
    }

}
