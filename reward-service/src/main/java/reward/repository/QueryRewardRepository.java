package reward.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import reward.entity.Reward;

@Repository
public interface QueryRewardRepository extends MongoRepository<Reward, String> {

}
