package reward.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Document(collection = "reward")
@NoArgsConstructor
public class Reward {
    @Id
    private String id;
    private String name;
    private String quantity;
    private String type;
    private int price;

    @Transient
    private int typeConsumer;
}
