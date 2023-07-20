package student.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import student.entity.Student;

@Repository
public interface QueryStudentRepository extends MongoRepository<Student, String> {
}
