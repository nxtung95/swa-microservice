package element.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import element.entity.Element;
import element.object.ElementRequest;
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
public class ElementProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Value(value = "${spring.kafka.element.topic.name:elementTopic}")
    private String topicName;

    public Element save(ElementRequest element) throws JsonProcessingException {
        Element elementEntity = Element.builder()
                .id(element.getId())
                .type(element.getType())
                .price(element.getPrice())
                .typeConsumer(1)
                .build();
        String message = objectMapper.writeValueAsString(elementEntity);
        log.info("Add Element, sent message=[" + message + "]");
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
        return elementEntity;
    }

    public Element update(ElementRequest element) throws JsonProcessingException {

        Element elementEntity = Element.builder()
                .id(element.getId())
                .type(element.getType())
                .price(element.getPrice())
                .typeConsumer(2)
                .build();
        String message = objectMapper.writeValueAsString(elementEntity);
        log.info("Update Element, sent message=[" + message + "]");
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
        return elementEntity;
    }

    public Element delete(ElementRequest element) throws JsonProcessingException {
        Element elementEntity = Element.builder()
                .id(element.getId())
                .type(element.getType())
                .price(element.getPrice())
                .typeConsumer(3)
                .build();
        String message = objectMapper.writeValueAsString(elementEntity);
        log.info("Delete Element, sent message=[" + message + "]");
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
        return elementEntity;
    }

}
