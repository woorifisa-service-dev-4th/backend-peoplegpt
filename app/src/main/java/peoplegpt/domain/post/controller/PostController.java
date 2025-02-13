package peoplegpt.domain.post.controller;

import peoplegpt.domain.post.model.dto.response.PostDetailResponse;
import peoplegpt.domain.post.model.dto.response.PostListResponse;
import peoplegpt.domain.post.service.PostService;

public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

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
