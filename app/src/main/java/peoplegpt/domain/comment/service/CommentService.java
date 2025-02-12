package peoplegpt.domain.comment.service;

import peoplegpt.domain.comment.model.dto.request.CreateCommentRequest;
import peoplegpt.domain.comment.model.dto.request.DeleteRequest;
import peoplegpt.domain.comment.model.dto.request.ShowListRequest;
import peoplegpt.domain.comment.model.dto.request.UpdateRequest;
import peoplegpt.domain.comment.model.dto.response.CommentResponse;
import peoplegpt.domain.comment.model.dto.response.DeleteResponse;
import peoplegpt.domain.comment.model.dto.response.ShowListResponse;
import peoplegpt.domain.comment.model.dto.response.UpdateResponse;

public interface CommentService {

    // 댓글을 생성하는 메서드
    CommentResponse createComment(CreateCommentRequest request);

    // 댓글을 업데이트하는 메서드
    UpdateResponse updateComment(UpdateRequest request);

    // 댓글을 삭제하는 메서드
    DeleteResponse deleteComment(DeleteRequest request);

    // 댓글 목록을 조회하는 메서드
    ShowListResponse showCommentList(ShowListRequest request);
}
