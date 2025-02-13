package peoplegpt.domain.comment.model.dto.response;

public class DeleteCommentResponse {
    private final long commentId;

    public DeleteCommentResponse(long commentId){
        this.commentId=commentId;
    }

    public long getCommentId(){
        return commentId;
    }
}
