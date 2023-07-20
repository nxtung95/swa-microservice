package avatar.object;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvatarRequest {
	private String id;
	private List<Element> elements;
}
