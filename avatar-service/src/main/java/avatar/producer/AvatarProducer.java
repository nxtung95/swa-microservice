package avatar.producer;

import avatar.entity.Avatar;
import avatar.object.AvatarRequest;
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

@Service
@Slf4j
public class AvatarProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Value(value = "${spring.kafka.Avatar.topic.name:avatarTopic}")
    private String topicName;

    public Avatar save(AvatarRequest avatar) throws JsonProcessingException {
        Avatar avatarEntity = Avatar.builder()
                .id(avatar.getId())
                .elements(avatar.getElements())
                .typeConsumer(1)
                .build();
        String message = objectMapper.writeValueAsString(avatarEntity);
        log.info("Add Avatar, sent message=[" + message + "]");
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
        return avatarEntity;
    }

    public Avatar update(AvatarRequest avatar) throws JsonProcessingException {

        Avatar avatarEntity = Avatar.builder()
                .id(avatar.getId())
                .elements(avatar.getElements())
                .typeConsumer(2)
                .build();
        String message = objectMapper.writeValueAsString(avatarEntity);
        log.info("Update Avatar, sent message=[" + message + "]");
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
        return avatarEntity;
    }

    public Avatar delete(AvatarRequest avatar) throws JsonProcessingException {
        Avatar avatarEntity = Avatar.builder()
                .id(avatar.getId())
                .elements(avatar.getElements())
                .typeConsumer(3)
                .build();
        String message = objectMapper.writeValueAsString(avatarEntity);
        log.info("Delete Avatar, sent message=[" + message + "]");
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
        return avatarEntity;
    }

}
