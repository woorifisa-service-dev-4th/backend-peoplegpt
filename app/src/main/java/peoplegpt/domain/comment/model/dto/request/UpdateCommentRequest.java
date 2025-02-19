package peoplegpt.domain.comment.model.dto.request;

public class UpdateCommentRequest {
    private final int id;
    private final long userId;
    private final String content;

    public UpdateCommentRequest(int id, long userId, String content) {
        this.id = id;
        this.userId = userId;
        this.content = content;
    }

    public int getCommentId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }
}
