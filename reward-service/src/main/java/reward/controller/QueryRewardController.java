package reward.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reward.entity.Reward;
import reward.service.QueryRewardService;

import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class QueryRewardController {
    @Autowired
    private QueryRewardService queryRewardService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/rewards/{id}", method = RequestMethod.GET)
    public ResponseEntity<Reward> view(@PathVariable(value = "id") String id) {
        if (Strings.isNullOrEmpty(id)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Reward reward = queryRewardService.findRewardById(id);
            log.info("ViewReward: " + objectMapper.writeValueAsString(reward));
            if (Objects.isNull(reward)) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.ok(reward);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
