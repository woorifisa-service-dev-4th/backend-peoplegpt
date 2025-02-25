package com.peoplegpt.demo.domain.user.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class GetUserRequest {

    private final long userId;
}

