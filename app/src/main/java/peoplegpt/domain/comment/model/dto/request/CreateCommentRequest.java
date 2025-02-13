package peoplegpt.domain.comment.model.dto.request;

public class CreateCommentRequest {
    private final long userId;
    private final int postId;
    private final String content;

    public CreateCommentRequest(long userId, int postId, String content){
        this.userId=userId;
        this.postId=postId;
        this.content=content;
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

}
