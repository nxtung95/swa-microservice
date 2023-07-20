package reward.service;

import reward.entity.Reward;

public interface QueryRewardService {
    Reward findRewardById(String id);
}
