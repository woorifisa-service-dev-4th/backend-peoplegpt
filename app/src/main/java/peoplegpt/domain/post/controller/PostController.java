package peoplegpt.domain.post.controller;

import lombok.RequiredArgsConstructor;
import peoplegpt.domain.post.model.dto.response.PostDetailResponse;
import peoplegpt.domain.post.model.dto.response.PostListResponse;
import peoplegpt.domain.post.repository.PostRepository;
import peoplegpt.domain.post.service.PostService;
import peoplegpt.domain.post.service.impl.PostServiceImpl;

public class PostController {

    private final PostRepository postRepository = new PostRepository();
    private final PostService postService = new PostServiceImpl(postRepository);

    public PostListResponse displayPostsByCategory(String category) {
        PostListResponse response = postService.getPostsByCategory(category);
        if (response.getPosts().isEmpty()) {
            System.out.println("해당 카테고리의 게시물이 없습니다.");
        } else {
            System.out.println(response);
        }

        return response;
    }

    public PostDetailResponse displayPostByPostId(long postId) {
        PostDetailResponse response = postService.getPostByPostId(postId);
        return response;
    }
}
