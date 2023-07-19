package student.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Document(collection = "Student")
public class Student {
    @Id
    private String id;
    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    @Field(name = "student_number")
    private int studentNumber;
    private String school;
    @Field(name = "class")
    private String classStudent;
    private int score;
    private String avatar;
    private String rewards;
}
