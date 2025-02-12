package peoplegpt.domain.comment.model.dto.response;


public class UpdateResponse {
    private final String message;

    public UpdateResponse(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }
}
