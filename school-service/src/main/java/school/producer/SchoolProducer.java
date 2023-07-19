package school.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import school.controller.QuerySchoolController;
import school.entity.School;
import school.object.SchoolRequest;
import school.service.QuerySchoolService;

import java.util.UUID;

@Service
@Slf4j
public class SchoolProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Value(value = "${spring.kafka.school.topic.name:schoolTopic}")
    private String topicName;

    public void save(SchoolRequest school) throws JsonProcessingException {
        School schoolEntity = School.builder()
                .id(school.getId())
                .email(school.getEmail())
                .address(school.getAddress())
                .name(school.getName())
                .phone(school.getPhone())
                .type(1)
                .build();
        String message = objectMapper.writeValueAsString(schoolEntity);
        log.info("Add School, sent message=[" + message + "]");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {

            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

        });
    }

    public void update(SchoolRequest school) throws JsonProcessingException {

        School schoolEntity = School.builder()
                .id(school.getId())
                .email(school.getEmail())
                .address(school.getAddress())
                .name(school.getName())
                .phone(school.getPhone())
                .type(2)
                .build();
        String message = objectMapper.writeValueAsString(schoolEntity);
        log.info("Update School, sent message=[" + message + "]");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {

            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

        });
    }

    public void delete(SchoolRequest school) throws JsonProcessingException {
        School schoolEntity = School.builder()
                .id(school.getId())
                .email(school.getEmail())
                .address(school.getAddress())
                .name(school.getName())
                .phone(school.getPhone())
                .type(3)
                .build();
        String message = objectMapper.writeValueAsString(schoolEntity);
        log.info("Delete School, sent message=[" + message + "]");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {

            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

        });
    }

}
