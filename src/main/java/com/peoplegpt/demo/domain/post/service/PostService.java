package com.peoplegpt.demo.domain.post.service;

import org.springframework.http.ResponseEntity;

import com.peoplegpt.demo.domain.post.model.dto.response.PostDetailResponse;
import com.peoplegpt.demo.domain.post.model.dto.response.PostListResponse;

public interface PostService {
    
    public ResponseEntity<PostListResponse> getPostsByCategory(String category);
    
    public ResponseEntity<PostDetailResponse> getPostByPostId(long postId);
}
