package user.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	private String id;
	private String username;
	private String password;
	private String role;
}
