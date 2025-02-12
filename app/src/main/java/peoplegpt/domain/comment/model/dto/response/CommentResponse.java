package peoplegpt.domain.comment.model.dto.response;

import java.time.LocalDateTime;

import peoplegpt.domain.global.model.entity.DataStatus;

public class CommentResponse {
    private final long userId;
    private final int postId;
    private final String content;
    private final DataStatus status;
    private final LocalDateTime createdAt;

    public CommentResponse(long userId, int postId, String content){
        this.userId=userId;
        this.postId=postId;
        this.content=content;
        this.status=DataStatus.ACTIVE;
        this.createdAt=LocalDateTime.now();
    }

    public long getUserId(){
        return userId;
    }

    public int getPostId(){
        return postId;
    }

    public String getContent(){
        return content;
    }

    public DataStatus getDataStatus(){
        return status;
    }

    public LocalDateTime getLocalDateTime(){
        return createdAt;
    }
}
