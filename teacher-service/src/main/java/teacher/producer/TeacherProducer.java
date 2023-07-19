package teacher.producer;

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
import teacher.entity.Teacher;
import teacher.object.TeacherRequest;

@Service
@Slf4j
public class TeacherProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Value(value = "${spring.kafka.teacher.topic.name:teacherTopic}")
    private String topicName;

    @Value(value = "${spring.kafka.add.teacher.topic.name:addTeacherTopic}")
    private String addTeacherTopic;


    public Teacher save(TeacherRequest teacher) throws JsonProcessingException {
        Teacher teacherEntity = Teacher.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail())
                .phone(teacher.getPhone())
                .school(teacher.getSchool())
                .teachingClass(teacher.getTeachingClass())
                .type(1)
                .build();
        String message = objectMapper.writeValueAsString(teacherEntity);
        log.info("Add Teacher, sent message=[" + message + "]");
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(addTeacherTopic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error(ex.getMessage());
            }

        });
        return teacherEntity;
    }

    public Teacher update(TeacherRequest teacher) throws JsonProcessingException {

        Teacher teacherEntity = Teacher.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail())
                .phone(teacher.getPhone())
                .school(teacher.getSchool())
                .teachingClass(teacher.getTeachingClass())
                .type(2)
                .build();
        String message = objectMapper.writeValueAsString(teacher);
        log.info("Update Teacher, sent message=[" + message + "]");
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
        return teacherEntity;
    }

    public Teacher delete(TeacherRequest teacher) throws JsonProcessingException {
        Teacher teacherEntity = Teacher.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail())
                .phone(teacher.getPhone())
                .school(teacher.getSchool())
                .teachingClass(teacher.getTeachingClass())
                .type(3)
                .build();
        String message = objectMapper.writeValueAsString(teacherEntity);
        log.info("Delete Teacher, sent message=[" + message + "]");
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
        return teacherEntity;
    }

}
