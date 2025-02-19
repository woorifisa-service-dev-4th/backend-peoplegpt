package peoplegpt.domain.comment.model.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import peoplegpt.domain.comment.model.entity.Comment;

@Getter
@Builder
@AllArgsConstructor
public class GetCommentListResponse {
    private final List<Comment> comments;

}
