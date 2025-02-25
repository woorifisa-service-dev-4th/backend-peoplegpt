package com.peoplegpt.demo.domain.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.peoplegpt.demo.domain.post.model.dto.response.PostDetailResponse;
import com.peoplegpt.demo.domain.post.model.dto.response.PostListResponse;
import com.peoplegpt.demo.domain.post.service.PostService;

import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Post", description = "게시물 관련 API")
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * GET 카테고리별 게시물들 조회
     * [GET] /post/category/{category}
     * @param category
     * @return PostListResponse
     */
    @Operation(summary = "카테고리별 게시물들 조회", description = "API호출 예제: /post/category/자유")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 404, message = "게시물 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/category/{category}")
    public ResponseEntity<PostListResponse> displayPostsByCategory(String category) {
        return postService.getPostsByCategory(category);
    }

    public ResponseEntity<PostDetailResponse> displayPostByPostId(long postId) {
        return postService.getPostByPostId(postId);
    }
}
