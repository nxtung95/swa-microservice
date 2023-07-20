package avatar.repository;

import avatar.entity.Avatar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryAvatarRepository extends MongoRepository<Avatar, String> {

}
