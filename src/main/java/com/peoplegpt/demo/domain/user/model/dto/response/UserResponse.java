package com.peoplegpt.demo.domain.user.model.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.peoplegpt.demo.domain.user.model.entity.UserRole;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {
    private final long userId;
    private final String email;
    private final String name;
    private final UserRole role;
    private final LocalDateTime createdAt;
}
