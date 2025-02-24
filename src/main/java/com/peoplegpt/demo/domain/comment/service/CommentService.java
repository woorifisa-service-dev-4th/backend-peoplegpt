package com.peoplegpt.demo.domain.comment.service;

import com.peoplegpt.demo.domain.comment.model.dto.request.CreateCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.request.DeleteCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.request.GetCommentListRequest;
import com.peoplegpt.demo.domain.comment.model.dto.request.UpdateCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.response.CreateCommentResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.DeleteCommentResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.GetCommentListResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.UpdateCommentResponse;

public interface CommentService {

    // 댓글을 생성하는 메서드
    CreateCommentResponse createComment(CreateCommentRequest request);

    // 댓글을 업데이트하는 메서드
    UpdateCommentResponse updateComment(UpdateCommentRequest request);

    // 댓글을 삭제하는 메서드
    DeleteCommentResponse deleteComment(DeleteCommentRequest request);

    // 댓글 목록을 조회하는 메서드
    GetCommentListResponse getCommentList(GetCommentListRequest request);
}
