package peoplegpt.domain.comment.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdateCommentResponse {
    private final long commentId;

}
