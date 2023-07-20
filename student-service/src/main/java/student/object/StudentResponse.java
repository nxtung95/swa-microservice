package student.object;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import student.entity.Student;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class StudentResponse extends BaseResponse {
    private Student student;
}
