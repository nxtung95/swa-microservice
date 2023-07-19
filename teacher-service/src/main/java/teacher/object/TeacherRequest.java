package teacher.object;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class TeacherRequest {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String school;
	private TeachingClass teachingClass;
}
