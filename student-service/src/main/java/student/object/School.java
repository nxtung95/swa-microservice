package student.object;

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
@NoArgsConstructor
public class School {
    private String id;
    private String name;
    private String address;
    private String email;
    private String phone;
}
