package com.peoplegpt.demo.domain.user.model.dto.response;

import com.peoplegpt.demo.domain.user.model.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignInResponse {
    private final long userId;
    private final String token;
    private final UserRole role;

}
