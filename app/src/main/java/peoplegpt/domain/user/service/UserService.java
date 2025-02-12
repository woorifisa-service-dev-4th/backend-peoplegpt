package peoplegpt.domain.user.service;

import peoplegpt.domain.user.model.dto.request.GetUserRequest;
import peoplegpt.domain.user.model.dto.request.SignInRequest;
import peoplegpt.domain.user.model.dto.request.SignUpRequest;
import peoplegpt.domain.user.model.dto.response.SignInResponse;
import peoplegpt.domain.user.model.dto.response.SignUpResponse;
import peoplegpt.domain.user.model.dto.response.UserResponse;

public interface UserService {
    public SignInResponse signIn(SignInRequest request);
    
    public SignUpResponse signUp(SignUpRequest request);

    public UserResponse getUser(GetUserRequest userId);

}
