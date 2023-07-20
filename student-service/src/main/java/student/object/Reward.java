package student.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Reward {
    private String id;
    private String name;
    private String quantity;
    private String type;
    private int price;

}
