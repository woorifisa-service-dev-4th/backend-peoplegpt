package com.peoplegpt.demo.domain.post.model.dto.response;

import com.peoplegpt.demo.domain.post.model.entity.Category;
import com.peoplegpt.demo.domain.post.model.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MentorPostDto {
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private Category category;
    private LocalDateTime createdAt;
    
    public static MentorPostDto from(Post post) {
        return MentorPostDto.builder()
                .postId(post.getPostId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .createdAt(post.getCreatedAt())
                .build();
    }
}