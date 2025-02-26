package com.peoplegpt.demo.domain.comment.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.peoplegpt.demo.domain.comment.model.dto.request.CreateCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.request.DeleteCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.request.UpdateCommentRequest;
import com.peoplegpt.demo.domain.comment.model.dto.response.CreateCommentResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.DeleteCommentResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.GetCommentListResponse;
import com.peoplegpt.demo.domain.comment.model.dto.response.UpdateCommentResponse;
import com.peoplegpt.demo.domain.comment.model.entity.Comment;
import com.peoplegpt.demo.domain.comment.repository.CommentRepository;
import com.peoplegpt.demo.domain.comment.service.CommentService;
import com.peoplegpt.demo.domain.user.model.entity.UserRole;
import com.peoplegpt.demo.domain.user.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService{
    
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

    @Override
    //댓글 조회(특정 post) postId를 콘솔에서 입력 받아서 해당 post에 해당하는 comment를 조회..
    public ResponseEntity<GetCommentListResponse> getCommentList(long postId){
        List<Comment> comments = commentRepository.findCommentsByPostId(postId);

        if(comments == null || comments.isEmpty()){
            logger.info("댓글이 존재하지 않습니다.");
            return ResponseEntity.status(404)
                    .body(GetCommentListResponse.builder()
                        .comments(null)
                        .build());
        }

        return ResponseEntity.ok()
                .body(GetCommentListResponse.builder()
                    .comments(comments)
                    .build());
    }

    @Override
    @Transactional
    public ResponseEntity<CreateCommentResponse> createComment(CreateCommentRequest request) {
        long response;
        response = commentRepository.addComment(request.getUserId(), request.getPostId(), request.getContent());

        if(response == -1){
            logger.info("댓글 작성에 실패했습니다.");
            return ResponseEntity.status(500)
                    .body(CreateCommentResponse.builder()
                        .commentId(-1)
                        .build());
        }

        return ResponseEntity.ok()
                .body(CreateCommentResponse.builder()
                    .commentId(response)
                    .build());
    }

    @Override
    @Transactional
    public ResponseEntity<UpdateCommentResponse> updateComment(UpdateCommentRequest request) {
        long requestUserId = request.getUserId();
        Comment comment = commentRepository.findCommentByCommentId(request.getCommentId());
        
        if (comment == null) {
            logger.info("댓글이 존재하지 않습니다.");
            return ResponseEntity.status(404)
                    .body(UpdateCommentResponse.builder()
                        .commentId(-1)
                        .build());
        }

        long commentOwnerId = comment.getUserId();
        if (requestUserId != commentOwnerId) {
            logger.info("작성자가 아니라 수정할 수 없습니다.");
            return ResponseEntity.status(401)
                    .body(UpdateCommentResponse.builder()
                        .commentId(-1)
                        .build());
        }

        boolean result = commentRepository.updateComment(request.getCommentId(), request.getContent());
        if (!result) {
            logger.info("댓글 수정에 실패했습니다.");
            return ResponseEntity.status(500)
                    .body(UpdateCommentResponse.builder()
                        .commentId(-1)
                        .build());
        }

        return ResponseEntity.ok()
                .body(UpdateCommentResponse.builder()
                    .commentId(request.getCommentId())
                    .build());
    }

    @Override
    @Transactional
    public ResponseEntity<DeleteCommentResponse> deleteComment(DeleteCommentRequest request){
        long commentId = request.getCommentId();

        Comment comment = commentRepository.findCommentByCommentId(commentId);
        if(comment == null){
            logger.info("댓글이 존재하지 않습니다.");
            return ResponseEntity.status(404)
                    .body(DeleteCommentResponse.builder()
                        .commentId(-1)
                        .build());
        }

        long requestUserId = request.getUserId();
        if(userRepository.getUserRole(requestUserId) == UserRole.ADMIN ||
            comment.getUserId() == requestUserId){
            boolean result = commentRepository.deleteComment(commentId);
            if(!result){
                logger.info("댓글 삭제에 실패했습니다.");
                return ResponseEntity.status(500)
                        .body(DeleteCommentResponse.builder()
                            .commentId(-1)
                            .build());
            }
        } else{
            logger.info("작성자가 아니라 삭제할 수 없습니다.");
            return ResponseEntity.status(401)
                    .body(DeleteCommentResponse.builder()
                        .commentId(-1)
                        .build());
        }

        return ResponseEntity.ok()
                .body(DeleteCommentResponse.builder()
                    .commentId(commentId)
                    .build());
    }
}
