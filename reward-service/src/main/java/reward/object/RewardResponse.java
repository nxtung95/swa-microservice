package reward.object;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import reward.entity.Reward;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class RewardResponse extends BaseResponse {
	private Reward reward;
}
