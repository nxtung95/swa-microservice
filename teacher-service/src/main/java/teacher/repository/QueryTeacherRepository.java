package teacher.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import teacher.entity.Teacher;

@Repository
public interface QueryTeacherRepository extends MongoRepository<Teacher, String> {
}
