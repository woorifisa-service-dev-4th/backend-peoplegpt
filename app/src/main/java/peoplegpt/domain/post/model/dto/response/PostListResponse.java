package peoplegpt.domain.post.model.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import peoplegpt.domain.post.model.entity.Post;

@Getter
@Builder
@AllArgsConstructor
public class PostListResponse {
    private final List<Post> posts;

}
