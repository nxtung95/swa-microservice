package avatar.consumer;

import avatar.entity.Avatar;
import avatar.repository.CommandAvatarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class AvatarConsumer {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CommandAvatarRepository avatarRepository;

	@KafkaListener(topics = "avatarTopic", groupId = "SWA_Project")
	public void handlingAvatar(String message) {
		try {
			log.info("Received Message: " + message);
			Avatar Avatar = objectMapper.readValue(message, Avatar.class);
			if (Avatar.getTypeConsumer() == 1) {
				Avatar newAvatar = avatarRepository.insert(Avatar);
				if (!Objects.isNull(newAvatar)) {
					log.info("Add Avatar success, id: " + newAvatar.getId());
				} else {
					log.info("Add Avatar fail!");
				}
			} else if (Avatar.getTypeConsumer() == 2) {
				Avatar newAvatar = avatarRepository.save(Avatar);
				if (!Objects.isNull(newAvatar)) {
					log.info("Update Avatar success, id: " + newAvatar.getId());
				} else {
					log.info("Update Avatar fail!");
				}
			} else if (Avatar.getTypeConsumer() == 3) {
				avatarRepository.delete(Avatar);
				log.info("Delete Avatar success, id: " + Avatar.getId());
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
