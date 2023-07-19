package teacher.object;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Field;
import teacher.entity.Teacher;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class TeacherResponse extends BaseResponse {
	private Teacher teacher;
}
