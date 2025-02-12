package peoplegpt.domain.comment.controller;

import peoplegpt.domain.comment.model.dto.request.CreateCommentRequest;
import peoplegpt.domain.comment.model.dto.request.DeleteRequest;
import peoplegpt.domain.comment.model.dto.request.ShowListRequest;
import peoplegpt.domain.comment.model.dto.request.UpdateRequest;
import peoplegpt.domain.comment.model.dto.response.CommentResponse;
import peoplegpt.domain.comment.model.dto.response.DeleteResponse;
import peoplegpt.domain.comment.model.dto.response.ShowListResponse;
import peoplegpt.domain.comment.model.dto.response.UpdateResponse;
import peoplegpt.domain.comment.service.CommentService;

public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

    public ShowListResponse showCommentList(ShowListRequest request){
        return commentService.showCommentList(request);
    }

    public CommentResponse createComment(CreateCommentRequest request){
        return commentService.createComment(request);
    }

    public UpdateResponse updateComment(UpdateRequest request){
        return commentService.updateComment(request);
    }

    public DeleteResponse deleteComment(DeleteRequest request){
        return commentService.deleteComment(request);
    }
}
