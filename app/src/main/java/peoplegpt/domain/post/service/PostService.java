package peoplegpt.domain.post.service;

import peoplegpt.domain.post.model.dto.response.PostListResponse;

public interface PostService {
    public PostListResponse getPostsByCategory(String category);
}
