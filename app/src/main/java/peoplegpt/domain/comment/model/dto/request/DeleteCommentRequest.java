package peoplegpt.domain.comment.model.dto.request;

public class DeleteCommentRequest {
    private final int id;
    private final long userId;

    public DeleteCommentRequest(int id, long userId) {
        this.id = id;
        this.userId = userId;
    }

    public int getCommentId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }
}
