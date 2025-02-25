package com.peoplegpt.demo.domain.user.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class SignUpRequest {
    private final String email;
    private final String password;
    private final String name;
}
