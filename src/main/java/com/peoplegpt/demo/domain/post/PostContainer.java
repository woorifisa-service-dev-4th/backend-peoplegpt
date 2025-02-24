package com.peoplegpt.demo.domain.post;

import com.peoplegpt.demo.domain.post.controller.PostController;

public class PostContainer {

    private static final PostController postController = new PostController();

    public static PostController getPostController() {
        return postController;
    }
}
