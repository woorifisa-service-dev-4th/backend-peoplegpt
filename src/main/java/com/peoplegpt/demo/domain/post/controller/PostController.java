package com.peoplegpt.demo.domain.post.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

import com.peoplegpt.demo.domain.global.model.response.GlobalApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peoplegpt.demo.domain.global.model.entity.ClassFilter;
import com.peoplegpt.demo.domain.post.model.dto.request.CreateMentorPostRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.CreatePostRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.DeleteMentorPostRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.DeletePostRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.GetPostListRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.UpdateMentorPostRequest;
import com.peoplegpt.demo.domain.post.model.dto.request.UpdatePostRequest;
import com.peoplegpt.demo.domain.post.model.dto.response.MentorPostDto;
import com.peoplegpt.demo.domain.post.model.dto.response.PostDto;
import com.peoplegpt.demo.domain.post.model.entity.Category;
import com.peoplegpt.demo.domain.post.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
     * [GET] /post?category={}filter{}
     * @param category
     * @return ResponseEntity<GlobalApiResponse<List<PostDto>>>
     */
    @Operation(summary = "카테고리별 게시물들 조회", description = "API호출 예제: /post/category/자유")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "게시물 없음")
    })
    @GetMapping("/category/{category}")
    public ResponseEntity<GlobalApiResponse<List<PostDto>>> getPostsByCategory(
        @RequestParam String category,
        @RequestParam String filter
        
    ) {
        return postService.getPostsByCategory(GetPostListRequest.builder()
            .category(Category.valueOf(category))
            .filter(ClassFilter.valueOf(filter))
            .build());
    }

    /**
     * GET 게시물 상세 조회
     * [GET] /post/{postId}
     * @param postId
     * @return ResponseEntity<GlobalApiResponse<PostDto>>
     */
    @Operation(summary = "게시물 상세 조회", description = "API호출 예제: /post/1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "게시물 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/{postId}")
    public ResponseEntity<GlobalApiResponse<PostDto>> getPostByPostId(@PathVariable long postId) {
        return postService.getPostByPostId(postId);
    }

    /**
     * POST 게시물 QNA 생성
     * [POST] /post/create
     * @param request
     * @return ResponseEntity<GlobalApiResponse<PostDto>>
     */
    @Operation(summary = "게시물 QNA 생성", description = "API호출 예제: /post/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "게시물 생성 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse<PostDto>> createQNAPost(
        @RequestBody CreatePostRequest request
    ){
        return postService.createQNAPost(request);
    }

    /**
     * POST 게시물 QNA 수정
     * [PATCH] /post/update
     * @param request
     * @return ResponseEntity<GlobalApiResponse<PostDto>>
     */
    @Operation(summary = "게시물 QNA 수정", description = "API호출 예제: /post/update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "게시물 수정 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PatchMapping("/update")
    public ResponseEntity<GlobalApiResponse<PostDto>> updateQNAPost(
        @RequestBody UpdatePostRequest request
    ){
        return postService.updateQNAPost(request);
    }

    /**
     * POST 게시물 QNA 삭제
     * [PATCH] /post/delete
     * @param request
     * @return ResponseEntity<GlobalApiResponse<Long>>
     */
    @Operation(summary = "게시물 QNA 삭제", description = "API호출 예제: /post/delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "게시물 삭제 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PatchMapping("/delete")
    public ResponseEntity<GlobalApiResponse<Long>> deleteQNAPost(
        @RequestBody DeletePostRequest request
    ){
        return postService.deleteQNAPost(request);
    }
    
    /**
     * POST 게시물 Codeshare,daily 생성
     * MENTOR만 가능
     * [POST] /post/mentor/create
     * @param request
     * @return ResponseEntity<GlobalApiResponse<MentorPostDto>>
     */
    @Operation(summary = "게시물 Codeshare,daily 생성", description = "API호출 예제: /post/mentor/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "게시물 생성 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/mentor/create")
    public ResponseEntity<GlobalApiResponse<MentorPostDto>> createMentorPost(
        @RequestBody CreateMentorPostRequest request
    ){
        return postService.createMentorPost(request);
    }

    /**
     * POST 게시물 Codeshare,daily 수정
     * MENTOR만 가능
     * [PATCH] /post/mentor/update
     * @param request
     * @return ResponseEntity<GlobalApiResponse<MentorPostDto>>
     */
    @Operation(summary = "게시물 Codeshare,daily 수정", description = "API호출 예제: /post/mentor/update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "게시물 수정 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PatchMapping("/mentor/update")
    public ResponseEntity<GlobalApiResponse<MentorPostDto>> updateMentorPost(
        @RequestBody UpdateMentorPostRequest request
    ){
        return postService.updateMentorPost(request);
    }

    /**
     * POST 게시물 Codeshare,daily 삭제
     * MENTOR, ADMIN만 가능
     * [PATCH] /post/mentor/delete
     * @param request
     * @return ResponseEntity<GlobalApiResponse<Long>>
     */
    @Operation(summary = "게시물 Codeshare,daily 삭제", description = "API호출 예제: /post/mentor/delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "게시물 삭제 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PatchMapping("/mentor/delete")
    public ResponseEntity<GlobalApiResponse<Long>> deleteMentorPost(
        @RequestBody DeleteMentorPostRequest request
    ){
        return postService.deleteMentorPost(request);
    }
}
