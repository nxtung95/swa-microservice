package client.object;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    private String id;
    private String firstName;
    private String lastName;
    private int studentNumber;
    private School school;
    private StudentClass classStudent;
    private int score;
    private Avatar avatar;
    private List<Reward> rewards;
}
