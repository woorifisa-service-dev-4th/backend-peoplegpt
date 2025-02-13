package peoplegpt.domain.comment.controller;

import peoplegpt.domain.comment.model.dto.request.CreateCommentRequest;
import peoplegpt.domain.comment.model.dto.request.DeleteCommentRequest;
import peoplegpt.domain.comment.model.dto.request.GetCommentListRequest;
import peoplegpt.domain.comment.model.dto.request.UpdateCommentRequest;
import peoplegpt.domain.comment.model.dto.response.CreateCommentResponse;
import peoplegpt.domain.comment.model.dto.response.DeleteCommentResponse;
import peoplegpt.domain.comment.model.dto.response.GetCommentListResponse;
import peoplegpt.domain.comment.model.dto.response.UpdateCommentResponse;
import peoplegpt.domain.comment.service.CommentService;

public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

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
