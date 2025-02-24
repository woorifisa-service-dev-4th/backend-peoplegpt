package com.peoplegpt.demo.domain.post.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetPostListRequest {
    private final String category;

}
