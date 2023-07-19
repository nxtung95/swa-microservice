package user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import user.entity.User;

@Repository
public interface QueryUserRepository extends MongoRepository<User, String> {
}
