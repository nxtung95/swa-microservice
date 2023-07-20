package avatar.repository;

import avatar.entity.Avatar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandAvatarRepository extends MongoRepository<Avatar, String>  {
}
