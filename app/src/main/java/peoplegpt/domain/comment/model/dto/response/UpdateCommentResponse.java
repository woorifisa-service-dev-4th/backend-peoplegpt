package peoplegpt.domain.comment.model.dto.response;


public class UpdateCommentResponse {
    private final long commentId;

    public UpdateCommentResponse(long commentId){
        this.commentId=commentId;
    }

    public long getCommentId(){
        return commentId;
    }
}
