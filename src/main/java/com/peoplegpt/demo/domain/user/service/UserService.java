package com.peoplegpt.demo.domain.user.service;

import com.peoplegpt.demo.domain.user.model.dto.request.GetUserRequest;
import com.peoplegpt.demo.domain.user.model.dto.request.SignInRequest;
import com.peoplegpt.demo.domain.user.model.dto.request.SignUpRequest;
import com.peoplegpt.demo.domain.user.model.dto.response.SignResponse;
import com.peoplegpt.demo.domain.user.model.dto.response.UserResponse;

public interface UserService {

    public SignResponse signIn(SignInRequest request);
    
    public SignResponse signUp(SignUpRequest request);

    public UserResponse getUser(GetUserRequest userId);

}
