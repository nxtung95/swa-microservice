package student.producer;

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
import student.entity.Student;
import student.object.BuyRemoveElementRequest;
import student.object.BuyRewardRequest;
import student.object.StudentRequest;

@Service
@Slf4j
public class StudentProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Value(value = "${spring.kafka.student.topic.name:studentTopic}")
    private String topicName;
    @Value(value = "${spring.kafka.add.student.topic.name:addStudentTopic}")
    private String addStudentTopic;
    @Value(value = "${spring.kafka.buy.element.student.topic.name:buyElementTopic}")
    private String buyElementTopic;
    @Value(value = "${spring.kafka.remove.element.student.topic.name:removeElementTopic}")
    private String removeElementTopic;
    @Value(value = "${spring.kafka.buy.reward.student.topic.name:buyRewardTopic}")
    private String buyRewardTopic;

    public Student save(StudentRequest student) throws JsonProcessingException {
        Student studentEntity = Student.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .studentNumber(student.getStudentNumber())
                .school(student.getSchool())
                .classStudent(student.getClassStudent())
                .score(student.getScore())
                .avatar(student.getAvatar())
                .rewards(student.getRewards())
                .type(1)
                .build();
        String message = objectMapper.writeValueAsString(studentEntity);
        log.info("Add Student, sent message=[" + message + "]");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(addStudentTopic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {

            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

        });

        return studentEntity;
    }

    public Student update(StudentRequest student) throws JsonProcessingException {

        Student studentEntity = Student.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .studentNumber(student.getStudentNumber())
                .school(student.getSchool())
                .classStudent(student.getClassStudent())
                .score(student.getScore())
                .avatar(student.getAvatar())
                .rewards(student.getRewards())
                .type(2)
                .build();
        String message = objectMapper.writeValueAsString(studentEntity);
        log.info("Update Student, sent message=[" + message + "]");
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
        return studentEntity;
    }

    public Student delete(StudentRequest student) throws JsonProcessingException {
        Student studentEntity = Student.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .studentNumber(student.getStudentNumber())
                .school(student.getSchool())
                .classStudent(student.getClassStudent())
                .score(student.getScore())
                .avatar(student.getAvatar())
                .rewards(student.getRewards())
                .type(3)
                .build();
        String message = objectMapper.writeValueAsString(studentEntity);
        log.info("Delete Student, sent message=[" + message + "]");
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
        return studentEntity;
    }

    public void buyElements(BuyRemoveElementRequest rq) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(rq);
        log.info("Buy element, sent message=[" + message + "]");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(buyElementTopic, message);
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

    public void removeElement(BuyRemoveElementRequest request) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(request);
        log.info("Remove element, sent message=[" + message + "]");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(removeElementTopic, message);
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

    public void buyRewards(BuyRewardRequest request) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(request);
        log.info("Buy reward, sent message=[" + message + "]");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(buyRewardTopic, message);
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
