package com.peoplegpt.demo.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peoplegpt.demo.domain.user.model.dto.request.GetUserRequest;
import com.peoplegpt.demo.domain.user.model.dto.request.SignInRequest;
import com.peoplegpt.demo.domain.user.model.dto.request.SignUpRequest;
import com.peoplegpt.demo.domain.user.model.dto.response.SignInResponse;
import com.peoplegpt.demo.domain.user.model.dto.response.SignUpResponse;
import com.peoplegpt.demo.domain.user.model.dto.response.UserResponse;
import com.peoplegpt.demo.domain.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "유저 관련 API")
@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET 유저 정보 조회
     * [GET] /user/info/{userId}
     * @param userId
     * @return UserResponse
     */
    @Operation(summary = "유저 정보 조회", description = "API호출 예제: /user/info/1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "유저 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/info/{userId}")
    public ResponseEntity<UserResponse> getUserInfo(
        @PathVariable("userId") long userId
    ) {
        return userService.getUser(GetUserRequest.builder()
            .userId(userId)
            .build()
        );
    }
    
    
    /**
     * POST 회원가입
     * [POST] /user/signUp
     * @param request
     * @return SignResponse
     */
    @Operation(summary = "회원가입", description = "회원가입 API: /user/signUp")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "409", description = "이미 존재하는 유저"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUpUser(
        @RequestBody SignUpRequest request
    ) {
        return userService.signUp(request);
    }

    /**
     * POST 로그인
     * [POST] /user/signIn
     * @param request
     * @return SignResponse
     */
    @Operation(summary = "로그인", description = "로그인 API: /user/signIn")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "정보 불일치"),
            @ApiResponse(responseCode = "404", description = "유저 정보 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signInUser(
        @RequestBody SignInRequest request
    ) {
        return userService.signIn(request);
    }

}
