package com.peoplegpt.demo.domain.comment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peoplegpt.demo.domain.comment.model.dto.request.CreateCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.request.DeleteCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.request.UpdateCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.response.CreateCommentResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.DeleteCommentResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.GetCommentListResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.UpdateCommentResponse;
import com.peoplegpt.demo.domain.comment.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Comment", description= "댓글 관련 API")
@RestController
@RequestMapping("/comment")
public class CommentController {
    
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    /**
     * GET 댓글 리스트 조회
     * [GET] /postId=1
     * @param postId
     * @return GetCommentListResponse
     */
    @Operation(summary = "댓글 리스트 조회", description = "API호출 예제: /comment/list?postId=1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "댓글 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/list/{postId}")
    public ResponseEntity<GetCommentListResponse> getCommentList(
        @RequestParam long postId
    ){
        return commentService.getCommentList(postId);
    }

    /**
     * POST 댓글 생성
     * [POST] /create
     * @param request
     * @return
     */
    @Operation(summary = "댓글 생성", description = "API호출 예제: /comment/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "댓글 생성 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/create")
    public ResponseEntity<CreateCommentResponse> createComment(
        @RequestBody CreateCommentRequest request
    ){
        return commentService.createComment(request);
    }

    /**
     * POST 댓글 수정
     * [POST] /update
     * @param request
     * @return
     */
    @Operation(summary = "댓글 수정", description = "API호출 예제: /comment/update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "댓글 수정 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/update")
    public ResponseEntity<UpdateCommentResponse> updateComment(
        @RequestBody UpdateCommentRequest request
    ){
        return commentService.updateComment(request);
    }

    /**
     * POST 댓글 삭제
     * [POST] /delete
     * @param request
     * @return
     */
    @Operation(summary = "댓글 삭제", description = "API호출 예제: /comment/delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "404", description = "댓글 삭제 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PatchMapping("/delete")
    public ResponseEntity<DeleteCommentResponse> deleteComment(
        @RequestBody DeleteCommentRequest request
    ){
        return commentService.deleteComment(request);
    }
}
