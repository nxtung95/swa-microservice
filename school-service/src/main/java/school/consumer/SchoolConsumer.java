package school.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import school.entity.School;
import school.repository.CommandSchoolRepository;

import java.util.Objects;

@Service
@Slf4j
public class SchoolConsumer {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CommandSchoolRepository schoolRepository;

	@KafkaListener(topics = "schoolTopic", groupId = "SWA_Project")
	public void handlingSchool(String message) {
		try {
			log.info("Received Message: " + message);
			School school = objectMapper.readValue(message, School.class);
			if (school.getType() == 1) {
				School newSchool = schoolRepository.insert(school);
				if (!Objects.isNull(newSchool)) {
					log.info("Add school success, id: " + newSchool.getId());
				} else {
					log.info("Add school fail!");
				}
			} else if (school.getType() == 2) {
				School newSchool = schoolRepository.save(school);
				if (!Objects.isNull(newSchool)) {
					log.info("Update school success, id: " + newSchool.getId());
				} else {
					log.info("Update school fail!");
				}
			} else if (school.getType() == 3) {
				schoolRepository.delete(school);
				log.info("Delete school success, id: " + school.getId());
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
