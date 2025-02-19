package peoplegpt.domain.comment;

import peoplegpt.domain.comment.controller.CommentController;
import peoplegpt.domain.comment.repository.CommentRepository;
import peoplegpt.domain.comment.service.CommentService;
import peoplegpt.domain.comment.service.impl.CommentServiceImpl;

public class CommentContainer {
    private static final CommentController commnetController = new CommentController();

    public static CommentController getCommentController() {
        return commnetController;
    }
}


