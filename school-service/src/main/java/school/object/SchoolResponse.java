package school.object;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class SchoolResponse extends BaseResponse {
	private String id;
	private String name;
	private String address;
	private String email;
	private String phone;
}
