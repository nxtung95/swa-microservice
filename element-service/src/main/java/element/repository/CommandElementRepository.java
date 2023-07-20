package element.repository;

import element.entity.Element;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandElementRepository extends MongoRepository<Element, String>  {
}
