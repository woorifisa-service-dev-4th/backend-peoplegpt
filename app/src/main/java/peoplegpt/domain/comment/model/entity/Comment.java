package peoplegpt.domain.comment.model.entity;

import java.time.LocalDateTime;

import peoplegpt.domain.global.model.entity.DataStatus;

public class Comment {
    private int id;
    private int postId;
    private long userId;
    private String content;
    private DataStatus status;
    private LocalDateTime createdAt;

    
    public Comment(int postId, long userId, String content){
        this.postId=postId;
        this.userId=userId;
        this.content=content;
        this.status=DataStatus.ACTIVE;
        this.createdAt=LocalDateTime.now();
    }
    
    public Comment(int id, int postId, long userId, String content, LocalDateTime createdAt, DataStatus status){
        this.id=id;
        this.postId=postId;
        this.userId=userId;
        this.content=content;
        this.status=status;
        this.createdAt=createdAt;
    }

    public int getCommentId(){
        return id;
    }

    public int getPostId(){
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
