package peoplegpt.domain.comment.model.dto.response;

public class CreateCommentResponse {
    private final long commentId;

    public CreateCommentResponse(long commentId){
        this.commentId=commentId;
    }

    public long getCommentId(){
        return commentId;
    }

}
