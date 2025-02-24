package com.peoplegpt.demo.domain.comment.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdateCommentRequest {
    private final long commentId;
    private final long userId;
    private final String content;

}
