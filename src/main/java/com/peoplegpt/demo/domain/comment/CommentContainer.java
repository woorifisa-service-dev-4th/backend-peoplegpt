package com.peoplegpt.demo.domain.comment;

import com.peoplegpt.demo.domain.comment.controller.CommentController;
import com.peoplegpt.demo.domain.comment.repository.CommentRepository;
import com.peoplegpt.demo.domain.comment.service.CommentService;
import com.peoplegpt.demo.domain.comment.service.impl.CommentServiceImpl;

public class CommentContainer {
    private static final CommentController commnetController = new CommentController();

    public static CommentController getCommentController() {
        return commnetController;
    }
}


