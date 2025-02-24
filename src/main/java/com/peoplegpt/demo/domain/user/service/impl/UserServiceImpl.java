package com.peoplegpt.demo.domain.user.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.peoplegpt.demo.domain.global.model.entity.DataStatus;
import com.peoplegpt.demo.domain.user.model.dto.request.GetUserRequest;
import com.peoplegpt.demo.domain.user.model.dto.request.SignInRequest;
import com.peoplegpt.demo.domain.user.model.dto.request.SignUpRequest;
import com.peoplegpt.demo.domain.user.model.dto.response.SignResponse;
import com.peoplegpt.demo.domain.user.model.dto.response.UserResponse;
import com.peoplegpt.demo.domain.user.model.entity.User;
import com.peoplegpt.demo.domain.user.repository.UserRepository;
import com.peoplegpt.demo.domain.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    // 임시 데이터 베이스 역할
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public SignResponse signIn(SignInRequest request) {
        User user = userRepository.findUserByEmail(request.getEmail());
        if (user == null || user.getStatus() == DataStatus.INACTIVE) {
            logger.info(request.getEmail(), "User not found");
            System.out.println("User not found");
            return SignResponse.builder()
                    .email(null)
                    .build();
        }

        String password = request.getPassword();
        boolean check = user.checkPassword(password);

        if (!check) {
            logger.info(request.getEmail(), "Password not matched");
            System.out.println("Password not matched");
            return SignResponse.builder()
                    .email(null)
                    .build();
        }

        SignResponse response = SignResponse.builder()
                .email(user.getEmail())
                .build();
        System.out.println("Sign in success");
        return response;
    }

    @Override
    public SignResponse signUp(SignUpRequest request) {
        boolean isExist = userRepository.isExistUserByEmail(request.getEmail());

        if (isExist) {
            logger.info(request.getEmail(), "User already exists");
            System.out.println("User already exists");
            return SignResponse.builder()
                    .email(null)
                    .build();
        }
        
        String name = request.getName();
        String email = request.getEmail();
        String password = request.getPassword();
        System.out.println(name + " " + email + " " + password);

        User user = User.builder()
                .email(email)
                .password(password)
                .name(name)
                .build();

        userRepository.add(user);

        SignResponse response = SignResponse.builder()
                .email(user.getEmail())
                .build();
        return response;
    }

    @Override
    public UserResponse getUser(GetUserRequest request) {
        User user = userRepository.findUserByUserId(request.getUserId());
        UserResponse response = UserResponse.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
        return response;
    }
    
}
