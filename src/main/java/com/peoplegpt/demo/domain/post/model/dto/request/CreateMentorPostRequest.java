package com.peoplegpt.demo.domain.post.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.peoplegpt.demo.domain.global.model.entity.ClassFilter;
import com.peoplegpt.demo.domain.post.model.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMentorPostRequest {
    
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;
    
    private String title;
    
    @NotBlank(message = "내용은 필수입니다.")
    private String content;
    
    @NotNull(message = "카테고리는 필수입니다.")
    private Category category;

    @NotNull(message = "반 는 필수입니다.")
    private ClassFilter filter;
}