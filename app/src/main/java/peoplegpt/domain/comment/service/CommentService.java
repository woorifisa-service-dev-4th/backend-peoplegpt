package peoplegpt.domain.comment.service;

import peoplegpt.domain.comment.model.dto.request.CreateCommentRequest;
import peoplegpt.domain.comment.model.dto.request.DeleteCommentRequest;
import peoplegpt.domain.comment.model.dto.request.GetCommentListRequest;
import peoplegpt.domain.comment.model.dto.request.UpdateCommentRequest;
import peoplegpt.domain.comment.model.dto.response.CreateCommentResponse;
import peoplegpt.domain.comment.model.dto.response.DeleteCommentResponse;
import peoplegpt.domain.comment.model.dto.response.GetCommentListResponse;
import peoplegpt.domain.comment.model.dto.response.UpdateCommentResponse;

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
