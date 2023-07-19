package authen.object;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class LoginResponse {
    private String id;
    private String username;
    private String role;
    private String token;
}
