package com.peoplegpt.demo.domain.post.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteMentorPostRequest {
    
    @NotNull(message = "게시물 ID는 필수입니다.")
    private Long postId;
    
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;
}