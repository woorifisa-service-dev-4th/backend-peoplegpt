package peoplegpt.domain.user.model.dto.response;

public class SignResponse {
    String email;
    String name;

    public SignResponse(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
