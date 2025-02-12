package peoplegpt.domain.post.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import peoplegpt.domain.post.model.dto.response.PostListResponse;
import peoplegpt.domain.post.model.entity.Post;
import peoplegpt.domain.post.repository.PostRepository;
import peoplegpt.domain.post.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final List<Post> posts;

    private static final Logger logger = LogManager.getLogger(PostServiceImpl.class);

    public PostServiceImpl(PostRepository postRepository, List<Post> posts) {
        this.postRepository = postRepository;
        this.posts = posts;
    }

    @Override
    public PostListResponse getPostsByCategory(String category) {
        List<Post> posts = postRepository.getPostsByCategory(category);
        return new PostListResponse(posts);
    }
}
