package user.object;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import user.entity.User;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class UserResponse extends BaseResponse {
	private User user;
}
