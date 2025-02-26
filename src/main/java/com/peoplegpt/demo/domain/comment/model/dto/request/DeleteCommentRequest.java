package com.peoplegpt.demo.domain.comment.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DeleteCommentRequest {
    private final long commentId;
    private final long userId;
}
