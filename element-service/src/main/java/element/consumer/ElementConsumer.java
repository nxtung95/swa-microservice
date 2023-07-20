package element.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import element.entity.Element;
import element.repository.CommandElementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class ElementConsumer {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CommandElementRepository elementRepository;

	@KafkaListener(topics = "elementTopic", groupId = "SWA_Project")
	public void handlingElement(String message) {
		try {
			log.info("Received Message: " + message);
			Element element = objectMapper.readValue(message, Element.class);
			if (element.getTypeConsumer() == 1) {
				Element newElement = elementRepository.insert(element);
				if (!Objects.isNull(newElement)) {
					log.info("Add element success, id: " + newElement.getId());
				} else {
					log.info("Add element fail!");
				}
			} else if (element.getTypeConsumer() == 2) {
				Element newElement = elementRepository.save(element);
				if (!Objects.isNull(newElement)) {
					log.info("Update element success, id: " + newElement.getId());
				} else {
					log.info("Update element fail!");
				}
			} else if (element.getTypeConsumer() == 3) {
				elementRepository.delete(element);
				log.info("Delete element success, id: " + element.getId());
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
