package user.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user.entity.User;
import user.object.Teacher;
import user.service.CommandUserService;

import java.util.Objects;

@Service
@Slf4j
public class UserConsumer {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CommandUserService commandUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${spring.kafka.teacher.email.topic.name:teacherEmailTopic}")
	private String teacherEmailTopicName;

	@Value(value = "${user.default.password:test1234}")
	private String defaultPassword;

	@KafkaListener(topics = "addTeacherTopic", groupId = "SWA_Project_AddTeacher2")
	public void handlingAddUser(String message) {
		try {
			log.info("Received Message: " + message);
			Teacher teacher = objectMapper.readValue(message, Teacher.class);
			User user = User.builder()
					.username(teacher.getEmail())
					.password(passwordEncoder.encode(defaultPassword))
					.role("Teacher")
					.build();
			User newUser = commandUserService.save(user);
			if (!Objects.isNull(newUser)) {
				log.info("Add user, role teacher success, id: " + newUser.getId());
				kafkaTemplate.send(teacherEmailTopicName, objectMapper.writeValueAsString(newUser));
			} else {
				log.info("Add user, role teacher fail!");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
