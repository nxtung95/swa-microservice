package element.object;

import element.entity.Element;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class ElementResponse extends BaseResponse {
	private Element element;
}
