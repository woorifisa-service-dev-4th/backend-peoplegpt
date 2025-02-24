package com.peoplegpt.demo.domain.post.model.dto.response;

import com.peoplegpt.demo.domain.global.model.entity.ClassFilter;
import com.peoplegpt.demo.domain.global.model.entity.DataStatus;
import com.peoplegpt.demo.domain.post.model.entity.Category;
import com.peoplegpt.demo.domain.post.model.entity.Tag;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostDetailResponse {
    private final long postId;
    private final long userId;
    private String title;
    private String content;
    private final Category category;
    private ClassFilter filter;
    private Tag tag;
    private DataStatus status;
    private final LocalDateTime createdAt;

}
