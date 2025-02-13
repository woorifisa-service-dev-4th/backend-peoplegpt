package peoplegpt.domain.comment.model.entity;

import java.time.LocalDateTime;

import peoplegpt.domain.global.model.entity.DataStatus;

public class Comment {
    private long commentId;
    private final long postId;
    private final long userId;
    private String content;
    private DataStatus status;
    private final LocalDateTime createdAt;

    // Database Comment
    public Comment(long commentId, long postId, long userId, String content, LocalDateTime createdAt, DataStatus status){
        this.commentId=commentId;
        this.postId=postId;
        this.userId=userId;
        this.content=content;
        this.status=status;
        this.createdAt=createdAt;
    }

    // Create Comment
    public Comment(long commentId, long postId, long userId, String content){
        this.commentId=commentId;
        this.postId=postId;
        this.userId=userId;
        this.content=content;
        this.status=DataStatus.ACTIVE;
        this.createdAt=LocalDateTime.now();
    }

    public long getCommentId(){
        return commentId;
    }

    public long getPostId(){
        return postId;
    }

    public long getUserId(){
        return userId;
    }

    public String getContent(){
        return content;
    }

    public DataStatus getStatus(){
        return status;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public void updateContent(String newContent) {
        this.content = newContent;
    }

    public void deleteComment(){
        this.status=DataStatus.INACTIVE;
    }

}
