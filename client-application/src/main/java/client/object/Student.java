package client.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private int studentNumber;
    private School school;
    private StudentClass classStudent;
    private int score;
    private Avatar avatar;
    private List<Reward> rewards;
    private int type;
}
