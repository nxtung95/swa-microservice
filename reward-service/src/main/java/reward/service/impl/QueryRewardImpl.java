package reward.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reward.entity.Reward;
import reward.repository.QueryRewardRepository;
import reward.service.QueryRewardService;

@Service
@Slf4j
public class QueryRewardImpl implements QueryRewardService {
    @Autowired
    private QueryRewardRepository queryRewardRepository;

    @Override
    @Transactional(readOnly = true)
    public Reward findRewardById(String id) {
        return queryRewardRepository.findById(id).orElse(null);
    }
}
