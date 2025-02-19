package peoplegpt.domain.comment.model.dto.request;

public class CreateCommentRequest {
    private final long userId;
    private final long postId;
    private final String content;

    public CreateCommentRequest(long userId, long postId, String content){
        this.userId=userId;
        this.postId=postId;
        this.content=content;
    }

    public long getUserId() {
        return userId;
    }

    public long getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

}
