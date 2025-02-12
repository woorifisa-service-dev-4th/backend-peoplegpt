package peoplegpt.domain.comment.model.dto.request;

public class DeleteRequest {
    private final int id;
    private final long userId;

    public DeleteRequest(int id, long userId) {
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
