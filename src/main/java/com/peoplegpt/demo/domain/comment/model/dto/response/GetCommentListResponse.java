package com.peoplegpt.demo.domain.comment.model.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.peoplegpt.demo.domain.comment.model.entity.Comment;
import com.peoplegpt.demo.domain.post.model.entity.Post;

@Getter
@Builder
@AllArgsConstructor
public class GetCommentListResponse {
    private final List<Comment> comments;
    private final Post post;

}
