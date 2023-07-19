package teacher.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import teacher.object.TeachingClass;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Document(collection = "teacher")
@NoArgsConstructor
public class Teacher {
    @Id
    private String id;
    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    private String email;
    private String phone;
    private String school;
    @Field(name = "teaching_class")
    private TeachingClass teachingClass;

    @Transient
    private int type;
}
