package peoplegpt.domain.post.model.dto.response;

import java.util.List;

import peoplegpt.domain.post.model.entity.Post;

public class PostListResponse {
    private final List<Post> posts;

    public PostListResponse(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
