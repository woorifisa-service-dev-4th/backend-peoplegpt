package peoplegpt.domain.user.model.dto.request;

public class GetUserRequest {

    private final String email;

    public GetUserRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    
}
