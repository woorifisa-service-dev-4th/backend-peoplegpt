package peoplegpt.domain.user.controller;

import lombok.RequiredArgsConstructor;
import peoplegpt.domain.user.model.dto.request.GetUserRequest;
import peoplegpt.domain.user.model.dto.request.SignInRequest;
import peoplegpt.domain.user.model.dto.request.SignUpRequest;
import peoplegpt.domain.user.model.dto.response.SignResponse;
import peoplegpt.domain.user.model.dto.response.UserResponse;
import peoplegpt.domain.user.repository.UserRepository;
import peoplegpt.domain.user.service.UserService;
import peoplegpt.domain.user.service.impl.UserServiceImpl;

@RequiredArgsConstructor
public class UserController {
    
    private final UserRepository userRepository = new UserRepository();
    private final UserService userService = new UserServiceImpl(userRepository);
    
    public UserResponse getUserInfo(GetUserRequest request) {
        return userService.getUser(request);
    }

    public SignResponse signUpUser(SignUpRequest request) {
        return (SignResponse) userService.signUp(request);
    }
    
    public SignResponse signInUser(SignInRequest request) {
        return (SignResponse) userService.signIn(request);
    }
    

}
