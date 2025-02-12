package peoplegpt.domain.comment.model.dto.response;

public class DeleteResponse {
    private final String message;

    public DeleteResponse(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }
}
