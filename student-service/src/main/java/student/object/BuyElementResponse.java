package student.object;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class BuyElementResponse extends BaseResponse {
    private String studentId;
}
