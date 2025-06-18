package com.home.users.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.common.entities.dtos.UserDTO;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class UserEventManagerTest {

//    @ClassRule
//    public static KafkaContainer kafka =
//            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));

    private static final String TOPIC_NAME =  "USER_TOPIC";

    @Autowired
    private UserEventManager userEventManager;

    @Autowired
    public KafkaTemplate<String, UserEvent> template;

    @Captor
    private ArgumentCaptor<UserEvent> userEventArgumentCaptor;

    @Autowired
    private ObjectMapper objectMapper;

//    @Before
//    public void setUp() {
//        kafka.start();
//    }
//
//    @After
//    public void tearDown() {
//        kafka.stop();
//    }

    @Test
    public void testSendMessage() throws Exception {
        val jsonFile = new ClassPathResource("init/user.json").getFile();
        val toCreate = Files.readString(jsonFile.toPath());
        val dto = objectMapper.readValue(toCreate, UserDTO.class);


        userEventManager.sendMessage(UserEvent.builder()
                .message("user")
                .status("OK")
                .user(dto)
                .build());

        template.send(TOPIC_NAME, UserEvent.builder()
                .message("user")
                .status("OK")
                .user(dto)
                .build());
        //template.flush();
        Thread.sleep(3000);

        //assertThat(userEventManager.getUserDTO()).isEqualTo(dto);
    }

    public void testConsumeMessage() throws Exception {

    }
}
