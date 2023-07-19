package teacher.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import teacher.entity.Teacher;
import teacher.repository.CommandTeacherRepository;

import java.util.Objects;

@Service
@Slf4j
public class TeacherConsumer {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CommandTeacherRepository teacherRepository;

	@KafkaListener(topics = "addTeacherTopic", groupId = "SWA_Project")
	public void handlingAddTeacher(String message) {
		try {
			log.info("Received Message: " + message);
			Teacher teacher = objectMapper.readValue(message, Teacher.class);
			Teacher newTeacher = teacherRepository.insert(teacher);
			if (!Objects.isNull(newTeacher)) {
				log.info("Add teacher success, id: " + newTeacher.getId());
			} else {
				log.info("Add teacher fail!");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@KafkaListener(topics = "teacherTopic", groupId = "SWA_Project")
	public void handlingAddOrRemoveTeacher(String message) {
		try {
			log.info("Received Message: " + message);
			Teacher teacher = objectMapper.readValue(message, Teacher.class);
			if (teacher.getType() == 2) {
				Teacher newTeacher = teacherRepository.save(teacher);
				if (!Objects.isNull(newTeacher)) {
					log.info("Update teacher success, id: " + newTeacher.getId());
				} else {
					log.info("Update teacher fail!");
				}
			} else if (teacher.getType() == 3) {
				teacherRepository.delete(teacher);
				log.info("Delete teacher success, id: " + teacher.getId());
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
