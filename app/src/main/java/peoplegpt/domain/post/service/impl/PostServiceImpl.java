package peoplegpt.domain.post.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.RequiredArgsConstructor;
import peoplegpt.domain.post.model.dto.response.PostDetailResponse;
import peoplegpt.domain.post.model.dto.response.PostListResponse;
import peoplegpt.domain.post.model.entity.Category;
import peoplegpt.domain.post.model.entity.Post;
import peoplegpt.domain.post.repository.PostRepository;
import peoplegpt.domain.post.service.PostService;

@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

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
        PostListResponse response = PostListResponse.builder()
                .posts(posts)
                .build();

        return response;
    }

    @Override
    public PostDetailResponse getPostByPostId(long postId) {
        Post post = postRepository.findPostByPostId(postId);
        PostDetailResponse response = PostDetailResponse.builder()
                .postId(post.getPostId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .filter(post.getFilter())
                .tag(post.getTag())
                .status(post.getStatus())
                .createdAt(post.getCreatedAt())
                .build();
                
        return response;
    }
}
