package peoplegpt.domain.comment.model.dto.request;

public class GetCommentListRequest {
    private final long postId;

    public GetCommentListRequest(long postId) {
        this.postId=postId;
    }

    public long getPostId() {
        return postId;
    }
}
