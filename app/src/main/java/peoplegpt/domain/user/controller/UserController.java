package peoplegpt.domain.user.controller;

import peoplegpt.domain.user.model.dto.request.GetUserRequest;
import peoplegpt.domain.user.model.dto.request.SignInRequest;
import peoplegpt.domain.user.model.dto.request.SignUpRequest;
import peoplegpt.domain.user.model.dto.response.SignInResponse;
import peoplegpt.domain.user.model.dto.response.SignUpResponse;
import peoplegpt.domain.user.model.dto.response.UserResponse;
import peoplegpt.domain.user.service.UserService;

public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    public UserResponse getUserInfo(GetUserRequest request) {
        return userService.getUser(request);
    }

    public SignUpResponse signUpUser(SignUpRequest request) {
        return userService.signUp(request);
    }

    public SignInResponse signInUser(SignInRequest request) {
        return userService.signIn(request);
    }
    

}
