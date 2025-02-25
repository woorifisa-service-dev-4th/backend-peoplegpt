package com.peoplegpt.demo.domain.user.service;

import org.springframework.http.ResponseEntity;

import com.peoplegpt.demo.domain.user.model.dto.request.GetUserRequest;
import com.peoplegpt.demo.domain.user.model.dto.request.SignInRequest;
import com.peoplegpt.demo.domain.user.model.dto.request.SignUpRequest;
import com.peoplegpt.demo.domain.user.model.dto.response.SignInResponse;
import com.peoplegpt.demo.domain.user.model.dto.response.SignUpResponse;
import com.peoplegpt.demo.domain.user.model.dto.response.UserResponse;

public interface UserService {

    public ResponseEntity<SignInResponse> signIn(SignInRequest request);
    
    public ResponseEntity<SignUpResponse> signUp(SignUpRequest request);

    public ResponseEntity<UserResponse> getUser(GetUserRequest userId);

}
