package student.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import student.object.Avatar;
import student.object.Reward;
import student.object.School;
import student.object.StudentClass;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Document(collection = "student")
@NoArgsConstructor
public class Student {
    @Id
    private String id;
    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    @Field(name = "student_number")
    private int studentNumber;
    private School school;
    @Field(name = "class")
    private StudentClass classStudent;
    private int score;
    private Avatar avatar;
    private List<Reward> rewards;

    @Transient
    private int type;
}
