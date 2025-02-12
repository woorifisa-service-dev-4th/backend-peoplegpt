package peoplegpt.domain.post.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import peoplegpt.domain.post.model.dto.response.PostDetailResponse;
import peoplegpt.domain.post.model.dto.response.PostListResponse;
import peoplegpt.domain.post.model.entity.Category;
import peoplegpt.domain.post.model.entity.Post;
import peoplegpt.domain.post.repository.PostRepository;
import peoplegpt.domain.post.service.PostService;

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private static final Logger logger = LogManager.getLogger(PostServiceImpl.class);

    @Override
    public PostListResponse getPostsByCategory(String category) {
        Category cate = Category.valueOf(category);
        List<Post> posts;
        logger.info("게시물 목록을 조회합니다.");
        if (category == null) {
            posts = postRepository.getPosts();
        } else {
            posts = postRepository.getPostsByCategory(cate);

        }
        return new PostListResponse(posts);
    }

    @Override
    public PostDetailResponse getPostByPostId(long postId) {
        Post post = postRepository.findPostByPostId(postId);
        PostDetailResponse response = new PostDetailResponse(post.getPostId(),post.getUserId(), post.getTitle(), post.getContent(), post.getCategory(), post.getFilter(), post.getTag(), post.getStatus(), post.getCreatedAt());
        return response;
    }
}
