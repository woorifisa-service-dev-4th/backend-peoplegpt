package peoplegpt.domain.user.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignResponse {
    String email;
    String name;

    public SignResponse(String email) {
        this.email = email;
    }
}
