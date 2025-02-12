package peoplegpt.domain.post.model.dto.response;

import peoplegpt.domain.global.model.entity.ClassFilter;
import peoplegpt.domain.global.model.entity.DataStatus;
import peoplegpt.domain.post.model.entity.Category;
import peoplegpt.domain.post.model.entity.Post;
import peoplegpt.domain.post.model.entity.Tag;

import java.time.LocalDateTime;
import java.util.List;

public class PostListResponse {
    private List<Post> posts;

    public PostListResponse(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
