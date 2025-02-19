package peoplegpt.domain.comment.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateCommentRequest {
    private final long userId;
    private final long postId;
    private final String content;

}
