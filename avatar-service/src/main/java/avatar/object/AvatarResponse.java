package avatar.object;

import avatar.entity.Avatar;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class AvatarResponse extends BaseResponse {
	private Avatar avatar;
}
