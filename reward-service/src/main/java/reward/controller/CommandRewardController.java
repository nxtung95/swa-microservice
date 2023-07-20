package reward.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reward.entity.Reward;
import reward.object.RewardRequest;
import reward.object.RewardResponse;
import reward.producer.RewardProducer;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class CommandRewardController {
    @Autowired
    private RewardProducer rewardProducer;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/rewards", method = RequestMethod.POST)
    public ResponseEntity<RewardResponse> add(@RequestBody RewardRequest reward) {
        RewardResponse res = RewardResponse.builder().build();
        if (reward.getPrice() < 10 || reward.getPrice() > 50) {
            res.setDesc("Reward price have to greater than 50 and less than 10, please try again.");
            return ResponseEntity.badRequest().body(res);
        }
        try {
            reward.setId(UUID.randomUUID().toString());
            log.info("AddReward: " + objectMapper.writeValueAsString(reward));
            Reward rp = rewardProducer.save(reward);
            res.setDesc("Success");
            res.setReward(rp);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/rewards", method = RequestMethod.PUT)
    public ResponseEntity<RewardResponse> update(@RequestBody RewardRequest Reward) {
        RewardResponse res = RewardResponse.builder().build();
        if (Reward.getPrice() < 10 || Reward.getPrice() > 50) {
            res.setDesc("Reward price have to greater than 50 and less than 10, please try again.");
            return ResponseEntity.badRequest().body(res);
        }
        try {
            log.info("UpdateReward: " + objectMapper.writeValueAsString(Reward));
            Reward rp = rewardProducer.update(Reward);
            res.setDesc("Success");
            res.setReward(rp);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @RequestMapping(value = "/rewards", method = RequestMethod.DELETE)
    public ResponseEntity<RewardResponse> delete(@RequestBody RewardRequest Reward) {
        RewardResponse res = RewardResponse.builder().build();
        try {
            log.info("DeleteReward: " + objectMapper.writeValueAsString(Reward));
            Reward rp = rewardProducer.delete(Reward);
            res.setDesc("Success");
            res.setReward(rp);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.setDesc("Error happend");
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
