package authen.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Document(collection = "user")
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String role;
}
