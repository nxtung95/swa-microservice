package school.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Document(collection = "School")
public class School {
    @Id
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
}
