package com.peoplegpt.demo.domain.post.service;

import com.peoplegpt.demo.domain.post.model.dto.response.PostDetailResponse;
import com.peoplegpt.demo.domain.post.model.dto.response.PostListResponse;

public interface PostService {
    public PostListResponse getPostsByCategory(String category);
    public PostDetailResponse getPostByPostId(long postId);
}
