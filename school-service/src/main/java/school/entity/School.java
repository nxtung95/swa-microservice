package school.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Document(collection = "school")
@NoArgsConstructor
public class School {
    @Id
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;

    @Transient
    private int type;
}
