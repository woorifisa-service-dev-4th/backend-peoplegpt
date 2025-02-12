package peoplegpt.domain.comment.model.dto.response;

import java.util.List;

import peoplegpt.domain.comment.model.entity.Comment;

public class ShowListResponse {
    private List<Comment> comments;

    public ShowListResponse(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }
}
