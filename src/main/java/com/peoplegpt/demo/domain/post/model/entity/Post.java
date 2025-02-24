package com.peoplegpt.demo.domain.post.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.peoplegpt.demo.domain.global.model.entity.ClassFilter;
import com.peoplegpt.demo.domain.global.model.entity.DataStatus;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Post {
    private final long postId;
    private final long userId;
    private String title;
    private String content;
    private final Category category;
    private ClassFilter filter;
    private Tag tag;
    private DataStatus status;
    private final LocalDateTime createdAt;

    // Database init Codeshare, Daily 게시물 사용
    public Post(long postId, long userId, String title, String content, Category category, DataStatus status, LocalDateTime createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.status = status;
        this.createdAt = createdAt;
    }    

    // QNA 게시물 작성시 사용
    public Post(long postId, long userId, String title, String content, Category category, ClassFilter filter, Tag tag) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.filter = filter;
        this.tag = tag;
        this.status = DataStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    // Codeshare, Daily 게시물 작성시 사용
    public Post(long postId, long userId, String title, String content, Category category) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.status = DataStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updatePost(String title, String content, ClassFilter filter) {
        this.title = title;
        this.content = content;
        this.filter = filter;
    }

    public void updatePost(String title, String content, Tag tag) {
        this.title = title;
        this.content = content;
        this.tag = tag;
    }

    public void updatePost(String title, String content, ClassFilter filter, Tag tag) {
        this.title = title;
        this.content = content;
        this.filter = filter;
        this.tag = tag;
    }

    public void deletePost() {
        this.status = DataStatus.INACTIVE;
    }
}
