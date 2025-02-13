package peoplegpt.domain.user.service;

import peoplegpt.domain.user.model.dto.request.GetUserRequest;
import peoplegpt.domain.user.model.dto.request.SignInRequest;
import peoplegpt.domain.user.model.dto.request.SignUpRequest;
import peoplegpt.domain.user.model.dto.response.SignResponse;
import peoplegpt.domain.user.model.dto.response.UserResponse;

public interface UserService {

    public SignResponse signIn(SignInRequest request);
    
    public SignResponse signUp(SignUpRequest request);

    public UserResponse getUser(GetUserRequest userId);

}
