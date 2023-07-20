package email.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import email.object.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceConsumer {
	@Autowired
	private ObjectMapper objectMapper;

	@KafkaListener(topics = {"teacherEmailTopic", "studentEmailTopic"}, groupId = "SWA_Project_SendMail")
	public void sendMailToTeacher(String message) {
		try {
			log.info("Received Message: " + message);
			User user = objectMapper.readValue(message, User.class);
			log.info("Send an email to " + user.getUsername() + ", content: " + message + ". Please check for the detail.");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

}
