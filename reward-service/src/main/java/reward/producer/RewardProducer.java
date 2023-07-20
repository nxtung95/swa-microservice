package reward.producer;

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
import reward.entity.Reward;
import reward.object.RewardRequest;

@Service
@Slf4j
public class RewardProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Value(value = "${spring.kafka.reward.topic.name:rewardTopic}")
    private String topicName;

    public Reward save(RewardRequest reward) throws JsonProcessingException {
        Reward RewardEntity = Reward.builder()
                .id(reward.getId())
                .name(reward.getName())
                .type(reward.getType())
                .price(reward.getPrice())
                .quantity(reward.getQuantity())
                .typeConsumer(1)
                .build();
        String message = objectMapper.writeValueAsString(RewardEntity);
        log.info("Add Reward, sent message=[" + message + "]");
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
        return RewardEntity;
    }

    public Reward update(RewardRequest reward) throws JsonProcessingException {

        Reward rewardEntity = Reward.builder()
                .id(reward.getId())
                .name(reward.getName())
                .type(reward.getType())
                .price(reward.getPrice())
                .quantity(reward.getQuantity())
                .typeConsumer(2)
                .build();
        String message = objectMapper.writeValueAsString(rewardEntity);
        log.info("Update Reward, sent message=[" + message + "]");
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
        return rewardEntity;
    }

    public Reward delete(RewardRequest reward) throws JsonProcessingException {
        Reward rewardEntity = Reward.builder()
                .id(reward.getId())
                .name(reward.getName())
                .type(reward.getType())
                .price(reward.getPrice())
                .quantity(reward.getQuantity())
                .typeConsumer(3)
                .build();
        String message = objectMapper.writeValueAsString(rewardEntity);
        log.info("Delete Reward, sent message=[" + message + "]");
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
        return rewardEntity;
    }

}
