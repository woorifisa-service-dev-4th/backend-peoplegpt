package com.peoplegpt.demo.domain.post.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.peoplegpt.demo.domain.global.model.entity.ClassFilter;
import com.peoplegpt.demo.domain.post.model.entity.Tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePostRequest {
    
    @NotNull(message = "게시물 ID는 필수입니다.")
    private Long postId;
    
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;
    
    @NotBlank(message = "제목은 필수입니다.")
    private String title;
    
    @NotBlank(message = "내용은 필수입니다.")
    private String content;
    
    private ClassFilter filter;
    
    private Tag tag;
}