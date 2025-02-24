package com.peoplegpt.demo.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import com.peoplegpt.demo.domain.comment.model.dto.request.CreateCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.request.DeleteCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.request.GetCommentListRequest;
import com.peoplegpt.demo.domain.comment.model.dto.request.UpdateCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.response.CreateCommentResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.DeleteCommentResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.GetCommentListResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.UpdateCommentResponse;
import com.peoplegpt.demo.domain.comment.repository.CommentRepository;
import com.peoplegpt.demo.domain.comment.service.CommentService;
import com.peoplegpt.demo.domain.comment.service.impl.CommentServiceImpl;

public class CommentController {
    
    private final CommentRepository commentRepository = new CommentRepository();
    private final CommentService commentService = new CommentServiceImpl(commentRepository);

    public GetCommentListResponse getCommentList(GetCommentListRequest request){
        return commentService.getCommentList(request);
    }

    public CreateCommentResponse createComment(CreateCommentRequest request){
        return commentService.createComment(request);
    }

    public UpdateCommentResponse updateComment(UpdateCommentRequest request){
        return commentService.updateComment(request);
    }

    public DeleteCommentResponse deleteComment(DeleteCommentRequest request){
        return commentService.deleteComment(request);
    }
}
