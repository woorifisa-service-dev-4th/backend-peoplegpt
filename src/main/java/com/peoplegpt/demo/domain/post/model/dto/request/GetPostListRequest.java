package com.peoplegpt.demo.domain.post.model.dto.request;

import com.peoplegpt.demo.domain.global.model.entity.ClassFilter;
import com.peoplegpt.demo.domain.post.model.entity.Category;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시물 목록 조회 요청 DTO
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPostListRequest {
    
    @NotNull(message = "post 카테고리는 필수입니다.")
    private Category category;

    private ClassFilter filter;
}