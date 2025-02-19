package peoplegpt.domain.post.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import peoplegpt.domain.global.model.entity.ClassFilter;
import peoplegpt.domain.post.model.entity.Category;
import peoplegpt.domain.post.model.entity.Tag;

@Getter
@Builder
@AllArgsConstructor
public class PostRequest {
    private final long userId;
    private final String title;
    private final String content;
    private final Category category;
    private final ClassFilter filter;
    private final Tag tag;

}