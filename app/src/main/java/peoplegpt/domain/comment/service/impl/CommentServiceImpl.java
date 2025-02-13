package peoplegpt.domain.comment.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import peoplegpt.domain.comment.model.dto.request.CreateCommentRequest;
import peoplegpt.domain.comment.model.dto.request.DeleteCommentRequest;
import peoplegpt.domain.comment.model.dto.request.GetCommentListRequest;
import peoplegpt.domain.comment.model.dto.request.UpdateCommentRequest;
import peoplegpt.domain.comment.model.dto.response.CreateCommentResponse;
import peoplegpt.domain.comment.model.dto.response.DeleteCommentResponse;
import peoplegpt.domain.comment.model.dto.response.GetCommentListResponse;
import peoplegpt.domain.comment.model.dto.response.UpdateCommentResponse;
import peoplegpt.domain.comment.model.entity.Comment;
import peoplegpt.domain.comment.repository.CommentRepository;
import peoplegpt.domain.comment.service.CommentService;
import peoplegpt.domain.global.model.entity.DataStatus;

public class CommentServiceImpl implements CommentService{
    
    private final CommentRepository commentRepository;
    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
    }

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);


    @Override
    //댓글 조회(특정 post) postId를 콘솔에서 입력 받아서 해당 post에 해당하는 comment를 조회..
    public GetCommentListResponse getCommentList(GetCommentListRequest request){
        long postId = request.getPostId();
        List<Comment> postComments = commentRepository.findCommentsByPostId(postId);

        if (postComments.isEmpty()){
            System.out.println("댓글이 존재하지 않습니다.");
            logger.info("댓글이 존재하지 않습니다.");
        } else{
            logger.info(request.getPostId() + "번 게시글의 댓글 목록입니다.");
        }
        return new GetCommentListResponse(postComments);
    }

    @Override
    //댓글 작성 댓글 내용 입력 받아서...
    public CreateCommentResponse createComment(CreateCommentRequest request) {
        long postId = commentRepository.generateCommentId();
        Comment comment = new Comment(postId, request.getUserId(), request.getPostId(), request.getContent());
        commentRepository.addComment(comment);
        logger.info(comment.getCommentId() + "댓글이 생성되었습니다.");
        
        return new CreateCommentResponse(comment.getCommentId());
    }

    @Override
    //댓글 수정 수정할 내용을 콘솔에서 입력 받아 넘겨주기
    public UpdateCommentResponse updateComment(UpdateCommentRequest request) {
        
        Comment comment = commentRepository.findCommentByCommentId(request.getCommentId());
        if (comment == null) {
            logger.info("댓글이 존재하지 않습니다.");
            return new UpdateCommentResponse(-1);
        }

        if (comment.getUserId() != request.getUserId()) {
            logger.info("작성자가 아니라 수정할 수 없습니다.");
            return new UpdateCommentResponse(-1);
        }

        commentRepository.updateComment(comment);

        logger.info(request.getCommentId() + "댓글이 성공적으로 수정되었습니다.");
        return new UpdateCommentResponse(request.getCommentId());
    }

    @Override
    //댓글 삭제
    public DeleteCommentResponse deleteComment(DeleteCommentRequest request){
        int id = request.getCommentId();
        long userId = request.getUserId();

        Comment comment = commentRepository.findCommentByCommentId(id);
        if (comment == null) {
            logger.info("댓글이 존재하지 않습니다.");
            return new DeleteCommentResponse(-1);
        }

        if (comment.getUserId() != userId) {
            logger.info("작성자가 아니라 삭제할 수 없습니다.");
            return new DeleteCommentResponse(-1);
        }

        if (comment.getStatus() == DataStatus.INACTIVE) {
            logger.info("잘못된 접근입니다.");
            return new DeleteCommentResponse(-1);
        }

        comment.deleteComment();
        logger.info("댓글이 성공적으로 삭제되었습니다.");
        return new DeleteCommentResponse(id);
    }
}
