package com.peoplegpt.demo.domain.comment.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DeleteCommentResponse {
    private final long commentId;

}
