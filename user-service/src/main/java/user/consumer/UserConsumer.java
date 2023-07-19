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
import user.repository.CommandUserRepository;

import java.util.Objects;

@Service
@Slf4j
public class UserConsumer {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CommandUserRepository commandUserRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${spring.kafka.teacher.email.topic.name:teacherEmailTopic}")
	private String teacherEmailTopicName;

	@Value(value = "${user.default.password:test1234}")
	private String defaultPassword;

	@KafkaListener(topics = "addTeacherTopic", groupId = "SWA_Project_AddTeacher2")
	public void handlingAddUserWhenAddTeacher(String message) {
		try {
			log.info("Received Message: " + message);
			Teacher teacher = objectMapper.readValue(message, Teacher.class);
			User user = User.builder()
					.username(teacher.getEmail())
					.password(passwordEncoder.encode(defaultPassword))
					.role("Teacher")
					.build();
			User newUser = commandUserRepository.save(user);
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

	@KafkaListener(topics = "userTopic", groupId = "SWA_Project_UserTopic")
	public void handlingAddUserFromAdmin(String message) {
		try {
			log.info("Received Message: " + message);
			User user = objectMapper.readValue(message, User.class);
			if (user.getType() == 1) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				User newUser = commandUserRepository.insert(user);
				if (!Objects.isNull(newUser)) {
					log.info("Add user success, id: " + newUser.getId());
				} else {
					log.info("Add user fail!");
				}
			} else if (user.getType() == 2) {
				User newUser = commandUserRepository.save(user);
				if (!Objects.isNull(newUser)) {
					log.info("Update user success, id: " + newUser.getId());
				} else {
					log.info("Update user fail!");
				}
			} else if (user.getType() == 3) {
				commandUserRepository.delete(user);
				log.info("Delete user success, id: " + user.getId());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
