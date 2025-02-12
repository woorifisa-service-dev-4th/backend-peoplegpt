package peoplegpt.domain.comment.model.dto.request;

import java.time.LocalDateTime;

import peoplegpt.domain.global.model.entity.DataStatus;

public class CreateCommentRequest {
    private final long userId;
    private final int postId;
    private final String content;
    private final DataStatus status;
    private final LocalDateTime createdAt;

    public CreateCommentRequest(long userId, int postId, String content){
        this.userId=userId;
        this.postId=postId;
        this.content=content;
        this.status=DataStatus.ACTIVE;
        this.createdAt=LocalDateTime.now();
    }

    public long getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public DataStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
