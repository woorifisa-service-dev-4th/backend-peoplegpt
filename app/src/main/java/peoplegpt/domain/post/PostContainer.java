package peoplegpt.domain.post;

import peoplegpt.domain.post.controller.PostController;
import peoplegpt.domain.post.repository.PostRepository;
import peoplegpt.domain.post.service.PostService;
import peoplegpt.domain.post.service.impl.PostServiceImpl;

public class PostContainer {
    private static final PostRepository postRepository = new PostRepository();
    private static final PostService postService = new PostServiceImpl(postRepository);
    private static final PostController postController = new PostController(postService);

    public static PostController getPostController() {
        return postController;
    }
}
