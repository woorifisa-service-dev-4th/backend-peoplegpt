package com.peoplegpt.demo.domain.user.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peoplegpt.demo.domain.global.jwt.JwtProvider;
import com.peoplegpt.demo.domain.global.model.entity.DataStatus;
import com.peoplegpt.demo.domain.user.model.dto.request.GetUserRequest;
import com.peoplegpt.demo.domain.user.model.dto.request.SignInRequest;
import com.peoplegpt.demo.domain.user.model.dto.request.SignUpRequest;
import com.peoplegpt.demo.domain.user.model.dto.response.SignInResponse;
import com.peoplegpt.demo.domain.user.model.dto.response.SignUpResponse;
import com.peoplegpt.demo.domain.user.model.dto.response.UserResponse;
import com.peoplegpt.demo.domain.user.model.entity.User;
import com.peoplegpt.demo.domain.user.repository.UserRepository;
import com.peoplegpt.demo.domain.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    // 임시 데이터 베이스 역할
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public ResponseEntity<SignInResponse> signIn(SignInRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail());
        logger.info(request.getEmail(), ": User Login Request");
        if (user == null || user.getStatus() == DataStatus.INACTIVE) {
            return ResponseEntity.status(404)
                    .body(SignInResponse.builder()
                        .userId(-1)
                        .token(null)
                        .role(null)
                        .build());
        }

        if (!user.checkPassword(request.getPassword())) {
            logger.info(request.getEmail(), "Password not matched");
            return ResponseEntity.status(401)
                    .body(SignInResponse.builder()
                        .userId(-1)
                        .token(null)
                        .role(null)
                        .build());
        }

        String token = jwtProvider.generateToken(user);
        return ResponseEntity.ok()
                .body(SignInResponse.builder()
                    .userId(user.getUserId())
                    .token(token)
                    .role(user.getRole())
                    .build());
    }

    @Override
    public ResponseEntity<SignUpResponse> signUp(SignUpRequest request) {
        boolean isExist = userRepository.isExistUserByEmail(request.getEmail());

        if (isExist) {
            logger.info(request.getEmail(), "User already exists");
            System.out.println("User already exists");
            return ResponseEntity.status(409)
                    .body(SignUpResponse.builder()
                        .email(null)
                        .build());
        }
        
        try {
            User user = User.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .name(request.getName())
                    .build();
            userRepository.add(user);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(SignUpResponse.builder()
                        .email(e.getMessage())
                        .build());
        }

        return ResponseEntity.ok()
                .body(SignUpResponse.builder()
                    .email(request.getEmail())
                    .build());
    }

    @Override
    public ResponseEntity<UserResponse> getUser(GetUserRequest request) {
        User user = userRepository.findUserByUserId(request.getUserId());

        if(user == null || user.getStatus() == DataStatus.INACTIVE) {
            return ResponseEntity.status(404)
                    .body(null);
        }
        
        return ResponseEntity.ok()
                .body(UserResponse.builder()
                    .userId(user.getUserId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .role(user.getRole())
                    .createdAt(user.getCreatedAt())
                    .build());
    }
    
}
