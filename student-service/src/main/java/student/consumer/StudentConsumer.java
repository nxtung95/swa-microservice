package student.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import student.entity.Student;
import student.object.*;
import student.repository.CommandStudentRepository;
import student.service.QueryStudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class StudentConsumer {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CommandStudentRepository studentRepository;
	@Autowired
	private QueryStudentService queryStudentService;

	@KafkaListener(topics = "addStudentTopic", groupId = "SWA_Project_AddStudent1")
	public void handlingAddStudent(String message) {
		try {
			log.info("Received Message: " + message);
			Student student = objectMapper.readValue(message, Student.class);
			if (student.getType() == 1) {
				Student newStudent = studentRepository.insert(student);
				if (!Objects.isNull(newStudent)) {
					log.info("Add Student success, id: " + newStudent.getId());
				} else {
					log.info("Add Student fail!");
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@KafkaListener(topics = "studentTopic", groupId = "SWA_Project")
	public void handlingStudent(String message) {
		try {
			log.info("Received Message: " + message);
			Student student = objectMapper.readValue(message, Student.class);
			 if (student.getType() == 2) {
				Student newStudent = studentRepository.save(student);
				if (!Objects.isNull(newStudent)) {
					log.info("Update Student success, id: " + newStudent.getId());
				} else {
					log.info("Update Student fail!");
				}
			} else if (student.getType() == 3) {
				studentRepository.delete(student);
				log.info("Delete Student success, id: " + student.getId());
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@KafkaListener(topics = "buyElementTopic", groupId = "SWA_Project")
	public void buyElement(String message) {
		try {
			log.info("Received Message: " + message);
			BuyRemoveElementRequest request = objectMapper.readValue(message, BuyRemoveElementRequest.class);
			String studentId = request.getStudentId();
			Student student = queryStudentService.findStudentById(studentId);
			if (student == null) {
				log.info("Dont exist student");
				return;
			}
			AtomicInteger score = new AtomicInteger(student.getScore());
			Avatar currentAvatar = student.getAvatar();
			List<Element> currentElements = currentAvatar.getElements();
			List<Element> elements = currentElements;
			log.info("Start buy element, before: " + objectMapper.writeValueAsString(elements) + ", score: " + score.get());
			request.getElements().forEach(e -> {
				Optional<Element> optElement = currentElements.stream().filter(c -> c.getId().equals(e.getId())).findFirst();
				if (optElement.isPresent()) {
					// Remove element first, after then buy element
					Element currentE = optElement.get();

					//  Refunded to the student’s score
					score.addAndGet(currentE.getPrice());

					// Use score to buy new element
					score.addAndGet(-e.getPrice());

				} else {
					// Buy element
					elements.add(e);

					// Update score
					score.addAndGet(-e.getPrice());
				}
			});
			log.info("End buy element, after: " + objectMapper.writeValueAsString(elements) + ", score: " + score.get());
			currentAvatar.setElements(elements);
			student.setAvatar(currentAvatar);
			student.setScore(score.get());
			studentRepository.save(student);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@KafkaListener(topics = "removeElementTopic", groupId = "SWA_Project")
	public void removeElement(String message) {
		try {
			log.info("Received Message: " + message);
			BuyRemoveElementRequest request = objectMapper.readValue(message, BuyRemoveElementRequest.class);
			String studentId = request.getStudentId();
			Student student = queryStudentService.findStudentById(studentId);
			if (student == null) {
				log.info("Dont exist student");
				return;
			}
			AtomicInteger score = new AtomicInteger(student.getScore());
			Avatar currentAvatar = student.getAvatar();
			List<Element> currentElements = currentAvatar.getElements();
			List<Element> elements = currentElements;
			log.info("Start remove element, before: " + objectMapper.writeValueAsString(elements) + ", score: " + score.get());
			request.getElements().forEach(e -> {
				Optional<Element> optElement = currentElements.stream().filter(c -> c.getId().equals(e.getId())).findFirst();
				if (optElement.isPresent()) {
					// Remove element first, after then buy element
					Element currentE = optElement.get();
					elements.remove(currentE);

					//  Refunded to the student’s score
					score.addAndGet(currentE.getPrice());
				}
			});
			log.info("Start remove element, after: " + objectMapper.writeValueAsString(elements) + ", score: " + score.get());
			currentAvatar.setElements(elements);
			student.setAvatar(currentAvatar);
			student.setScore(score.get());
			studentRepository.save(student);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@KafkaListener(topics = "buyRewardTopic", groupId = "SWA_Project")
	public void buyReward(String message) {
		try {
			log.info("Received Message: " + message);
			BuyRewardRequest request = objectMapper.readValue(message, BuyRewardRequest.class);
			String studentId = request.getStudentId();
			Student student = queryStudentService.findStudentById(studentId);
			if (student == null) {
				log.info("Dont exist student");
				return;
			}
			List<Reward> currentRewards = student.getRewards();
			List<Reward> rewards = currentRewards;
			AtomicInteger score = new AtomicInteger(student.getScore());
			log.info("Start buy reward, before: " + objectMapper.writeValueAsString(rewards) + ", score: " + score.get());
			request.getRewards().forEach(e -> {
				rewards.add(e);
				score.addAndGet(-e.getPrice());
			});
			log.info("End buy reward, after: " + objectMapper.writeValueAsString(rewards) + ", score: " + score.get());
			student.setRewards(rewards);
			student.setScore(score.get());
			studentRepository.save(student);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
