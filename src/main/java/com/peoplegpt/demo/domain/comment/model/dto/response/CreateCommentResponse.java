package com.peoplegpt.demo.domain.comment.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateCommentResponse {
    private final long commentId;
}
