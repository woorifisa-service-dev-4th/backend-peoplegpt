package peoplegpt.domain.comment.model.dto.request;

public class ShowListRequest {
    private final int postId;

    public ShowListRequest(int postId) {
        this.postId=postId;
    }

    public int getPostId() {
        return postId;
    }
}
