package com.peoplegpt.demo.domain.comment.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetCommentListRequest {
    private final long postId;

}
