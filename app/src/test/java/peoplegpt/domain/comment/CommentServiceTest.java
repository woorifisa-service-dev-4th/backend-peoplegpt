package peoplegpt.domain.comment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

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
import peoplegpt.domain.comment.service.impl.CommentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    private CommentService commentService;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        commentService = new CommentServiceImpl(commentRepository);
        System.out.println("Setting up");
    }

 
    @Test
    void should_GetCommentList_When_Valid_Request() {
        // given
        given(commentRepository.findCommentsByPostId(1L)).willReturn(Collections.emptyList());
        GetCommentListRequest request = new GetCommentListRequest(1L);

        // when
        GetCommentListResponse response = commentService.getCommentList(request);

        // then
        assertTrue(response.getComments().isEmpty());
    }

    @Test
    void should_CreateComment_When_Valid_Request() {
        // given
        CreateCommentRequest request = new CreateCommentRequest(1L, 1, "테스트 댓글");
        Comment comment = new Comment(1L, 1, 1L, "테스트 댓글");

        given(this.commentRepository.generateCommentId()).willReturn(1L);

        // when
        CreateCommentResponse response = commentService.createComment(request);

        // then
        assertEquals(1L, response.getCommentId());
        then(commentRepository).should(times(1)).addComment(any(Comment.class));
    }

    @Test
    void should_UpdateComment_When_Valid_Request() {
        // given
        UpdateCommentRequest request = new UpdateCommentRequest(1L, 1L, "수정된 댓글");
        Comment comment = new Comment(1L, 1L, 1L, "기존 댓글");

        given(commentRepository.findCommentByCommentId(1L)).willReturn(comment);

        // when
        UpdateCommentResponse response = commentService.updateComment(request);

        // then
        assertEquals(1L, response.getCommentId());
        then(commentRepository).should(times(1)).updateComment(any(Comment.class));
    }

    @Test
    void should_DeleteComment_When_Valid_Request(){
        // given
        DeleteCommentRequest request = new DeleteCommentRequest(1L, 1L);
        Comment comment = new Comment(1L, 1L, 1L, "테스트 댓글");

        given(commentRepository.findCommentByCommentId(1L)).willReturn(comment);

        // when
        DeleteCommentResponse response = commentService.deleteComment(request);

        // then
        assertEquals(1L, response.getCommentId());
    }

    /*@Test
    @DisplayName("Test GetCommentList")
    public void testGetCommentList() {
        GetCommentListRequest request = new GetCommentListRequest(1L);

        GetCommentListResponse response = commentService.getCommentList(request);

        assertNotNull(response.getComments());
        //기존 댓글 개수 4개
        assertTrue(response.getComments().size()==4);
    }

    @Test
    @DisplayName("Test CreateComment")
    public void testCreateComment() {
        CreateCommentRequest request = new CreateCommentRequest(1L, 10, "테스트 댓글입니다.");

        CreateCommentResponse response = commentService.createComment(request);

        //기존 마지막 댓글 id=4, 성공적으로 생성 시 기존 id+=1;
        assertTrue(response.getCommentId() > 4, "댓글 생성에 실패했습니다.");
    }

    @Test
    @DisplayName("Test DeleteComment")
    public void testDeleteComment() {
        int id=1;
        long userId=1;

        DeleteCommentRequest request = new DeleteCommentRequest(id,userId);

        DeleteCommentResponse response = commentService.deleteComment(request);

        //삭제하려는 댓글의 id 일치 여부 확인
        assertEquals(id, response.getCommentId(), "삭제 실패했습니다.");
    }

    @Test
    @DisplayName("Test UpdateComment")
    public void testUpdateComment(){
        int id=1;
        long userId=1;
        String content="수정된 내용입니다.";

        UpdateCommentRequest request = new UpdateCommentRequest(id, userId, content);

        UpdateCommentResponse response = commentService.updateComment(request);

        //수정하려는 댓글의 id 일치 여부 확인
        assertEquals(id, response.getCommentId(), "수정 실패했습니다.");
    }*/
}
