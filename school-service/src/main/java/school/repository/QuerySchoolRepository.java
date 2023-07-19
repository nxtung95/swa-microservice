package school.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import school.entity.School;

@Repository
public interface QuerySchoolRepository extends MongoRepository<School, String> {
}
