package user.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
