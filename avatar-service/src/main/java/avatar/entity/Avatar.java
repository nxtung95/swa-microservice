package avatar.entity;

import avatar.object.Element;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@Document(collection = "avatar")
@NoArgsConstructor
public class Avatar {
    @Id
    private String id;
    private List<Element> elements;

    @Transient
    private int typeConsumer;
}
