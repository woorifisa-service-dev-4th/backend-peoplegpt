package com.peoplegpt.demo.domain.post.model.dto.response;

import com.peoplegpt.demo.domain.post.model.entity.Category;
import com.peoplegpt.demo.domain.global.model.entity.ClassFilter;
import com.peoplegpt.demo.domain.post.model.entity.Post;
import com.peoplegpt.demo.domain.post.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private Category category;
    private ClassFilter filter;
    private Tag tag;
    private LocalDateTime createdAt;
    
    public static PostDto from(Post post) {
        return PostDto.builder()
                .postId(post.getPostId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .filter(post.getFilter())
                .tag(post.getTag())
                .createdAt(post.getCreatedAt())
                .build();
    }
}