package authen.repository;

import authen.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends MongoRepository<User, String>  {
    Optional<User> findFirstByUsername(String username);
}
