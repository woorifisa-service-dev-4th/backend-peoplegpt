package peoplegpt.domain.comment.model.dto.request;

public class DeleteCommentRequest {
    private final long id;
    private final long userId;

    public DeleteCommentRequest(long id, long userId) {
        this.id = id;
        this.userId = userId;
    }

    public long getCommentId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }
}
