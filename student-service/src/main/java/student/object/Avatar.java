package student.object;

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
@NoArgsConstructor
public class Avatar {
    private String id;
    private List<Element> elements;
}
