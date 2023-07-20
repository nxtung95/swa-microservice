package reward.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RewardRequest {
	private String id;
	private String name;
	private String quantity;
	private String type;
	private int price;
}
