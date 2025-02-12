package peoplegpt.domain.comment.service.impl;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import peoplegpt.domain.comment.model.dto.request.CreateCommentRequest;
import peoplegpt.domain.comment.model.dto.request.DeleteRequest;
import peoplegpt.domain.comment.model.dto.request.ShowListRequest;
import peoplegpt.domain.comment.model.dto.request.UpdateRequest;
import peoplegpt.domain.comment.model.dto.response.CommentResponse;
import peoplegpt.domain.comment.model.dto.response.DeleteResponse;
import peoplegpt.domain.comment.model.dto.response.ShowListResponse;
import peoplegpt.domain.comment.model.dto.response.UpdateResponse;
import peoplegpt.domain.comment.model.entity.Comment;
import peoplegpt.domain.comment.repository.CommentRepository;
import peoplegpt.domain.comment.service.CommentService;
import peoplegpt.domain.global.model.entity.DataStatus;

public class CommentServiceImpl implements CommentService{
    
    private final CommentRepository commentRepository;
    private final List<Comment> comments;
    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository=commentRepository;
        this.comments=commentRepository.getComments();
    }

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);


    @Override
    //댓글 조회(특정 post) postId를 콘솔에서 입력 받아서 해당 post에 해당하는 comment를 조회..
    public ShowListResponse showCommentList(ShowListRequest request){
        Scanner scanner = new Scanner(System.in);
        System.out.print("댓글을 조회할 post의 ID를 입력하세요: ");
        int postId = scanner.nextInt();

        List<Comment> postComments = commentRepository.findCommentsByPostId(postId);

        if (postComments.isEmpty()){
            System.out.println("댓글이 존재하지 않습니다.");
            logger.info("댓글이 존재하지 않습니다.");
        }
        else{
            System.out.println("Comments for postId " + postId + ":");
            postComments.forEach(comment -> {
                System.out.println("UserId: " + comment.getUserId() + " Date: " + comment.getCreatedAt() + "\nContent: " + comment.getContent());
            });
        }
        return new ShowListResponse(postComments);
    }

    @Override
    //댓글 작성 댓글 내용 입력 받아서...
    public CommentResponse createComment(CreateCommentRequest request) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("댓글 내용을 입력하세요: ");
        String content = scanner.nextLine();
        int lastId = comments.isEmpty() ? 1 : comments.get(comments.size() - 1).getCommentId();
        int newId = lastId + 1; 
        Comment newComment = new Comment(newId, request.getPostId(), request.getUserId(), 
                                    content, request.getCreatedAt(), request.getStatus());
        commentRepository.addComment(newComment);
        System.out.println("댓글이 성공적으로 생성되었습니다.");
        logger.info("댓글이 성공적으로 생성되었습니다.");
        return new CommentResponse(newComment.getUserId(), newComment.getPostId(), newComment.getContent());
    }

    @Override
    //댓글 수정 수정할 내용을 콘솔에서 입력 받아 넘겨주기
    public UpdateResponse updateComment(UpdateRequest request) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("수정할 댓글 내용을 입력하세요: ");
        String newContent = scanner.nextLine();
        int id = request.getCommentId();
        long userId = request.getUserId();
        String message;

        Comment comment = commentRepository.findCommentById(id);
        if (comment == null) {
            message="댓글이 존재하지 않습니다.";
            System.out.println(message);
            logger.info(message);
            return new UpdateResponse(message);
        }

        if (comment.getUserId() != userId) {
            message="작성자가 아니라 수정할 수 없습니다.";
            System.out.println(message);
            logger.info(message);
            return new UpdateResponse(message);
        }

        comment.updateContent(newContent);
        commentRepository.updateComment(comment);

        message="댓글이 성공적으로 수정되었습니다.";
        logger.info(message);
        return new UpdateResponse(message);
    }

    @Override
    //댓글 삭제
    public DeleteResponse deleteComment(DeleteRequest request){
        int id = request.getCommentId();
        long userId = request.getUserId();
        String message;

        Comment comment = commentRepository.findCommentById(id);
        if (comment == null) {
            message="댓글이 존재하지 않습니다.";
            System.out.println(message);
            logger.info(message);
            return new DeleteResponse(message);
        }

        if (comment.getUserId() != userId) {
            message="작성자가 아니라 삭제할 수 없습니다.";
            System.out.println(message);
            logger.info(message);
            return new DeleteResponse(message);
        }

        if (comment.getStatus() == DataStatus.INACTIVE) {
            message="이미 삭제된 댓글입니다.";
            System.out.println(message);
            logger.info(message);
            return new DeleteResponse(message);
        }

        comment.deleteComment();
        commentRepository.updateComment(comment);
        
        message="댓글이 성공적으로 삭제되었습니다.";
        System.out.println(message);
        logger.info(message);
        return new DeleteResponse(message);
    }
}
