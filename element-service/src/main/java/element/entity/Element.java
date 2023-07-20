package element.entity;

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
@Document(collection = "element")
@NoArgsConstructor
public class Element {
    @Id
    private String id;
    private String type;
    private int price;

    @Transient
    private int typeConsumer;
}
