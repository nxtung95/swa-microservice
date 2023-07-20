package reward.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reward.entity.Reward;
import reward.repository.CommandRewardRepository;

import java.util.Objects;

@Service
@Slf4j
public class RewardConsumer {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CommandRewardRepository rewardRepository;

	@KafkaListener(topics = "rewardTopic", groupId = "SWA_Project")
	public void handlingReward(String message) {
		try {
			log.info("Received Message: " + message);
			Reward Reward = objectMapper.readValue(message, Reward.class);
			if (Reward.getTypeConsumer() == 1) {
				Reward newReward = rewardRepository.insert(Reward);
				if (!Objects.isNull(newReward)) {
					log.info("Add Reward success, id: " + newReward.getId());
				} else {
					log.info("Add Reward fail!");
				}
			} else if (Reward.getTypeConsumer() == 2) {
				Reward newReward = rewardRepository.save(Reward);
				if (!Objects.isNull(newReward)) {
					log.info("Update Reward success, id: " + newReward.getId());
				} else {
					log.info("Update Reward fail!");
				}
			} else if (Reward.getTypeConsumer() == 3) {
				rewardRepository.delete(Reward);
				log.info("Delete Reward success, id: " + Reward.getId());
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
