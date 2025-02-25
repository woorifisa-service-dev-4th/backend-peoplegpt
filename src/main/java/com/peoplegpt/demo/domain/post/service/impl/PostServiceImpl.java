package com.peoplegpt.demo.domain.post.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.peoplegpt.demo.domain.post.model.dto.response.PostDetailResponse;
import com.peoplegpt.demo.domain.post.model.dto.response.PostListResponse;
import com.peoplegpt.demo.domain.post.model.entity.Category;
import com.peoplegpt.demo.domain.post.model.entity.Post;
import com.peoplegpt.demo.domain.post.repository.PostRepository;
import com.peoplegpt.demo.domain.post.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private static final Logger logger = LogManager.getLogger(PostServiceImpl.class);

    @Override
    public ResponseEntity<PostListResponse> getPostsByCategory(String category) {
        Category cate = Category.valueOf(category);
        List<Post> posts;
        if (category == null) {
            posts = postRepository.getPosts();
        } else {
            posts = postRepository.getPostsByCategory(cate);
        }

        if(posts == null) {
            logger.error("Failed to get posts by category");
            return ResponseEntity.status(404)
            .body(PostListResponse.builder()
                .posts(null)
                .build());
                
        }
        return ResponseEntity.ok()
            .body(PostListResponse.builder()
                .posts(posts)
                .build());
    }

    @Override
    public ResponseEntity<PostDetailResponse> getPostByPostId(long postId) {
        Post post = postRepository.findPostByPostId(postId);
        if (post == null) {
            logger.error("Failed to find post by postId");
            return ResponseEntity.status(404)
                .body(PostDetailResponse.builder()
                    .postId(0)
                    .userId(0)
                    .title(null)
                    .content(null)
                    .category(null)
                    .filter(null)
                    .tag(null)
                    .status(null)
                    .createdAt(null)
                    .build());
        }
        return ResponseEntity.ok()
            .body(PostDetailResponse.builder()
                .postId(post.getPostId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .filter(post.getFilter())
                .tag(post.getTag())
                .status(post.getStatus())
                .createdAt(post.getCreatedAt())
                .build());

    }
}