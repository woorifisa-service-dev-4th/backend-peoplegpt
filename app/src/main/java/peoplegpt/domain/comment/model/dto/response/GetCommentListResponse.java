package peoplegpt.domain.comment.model.dto.response;

import java.util.List;

import peoplegpt.domain.comment.model.entity.Comment;

public class GetCommentListResponse {
    private final List<Comment> comments;

    public GetCommentListResponse(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
