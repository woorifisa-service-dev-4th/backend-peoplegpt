package peoplegpt.domain.post.model.dto.response;

import peoplegpt.domain.global.model.entity.ClassFilter;
import peoplegpt.domain.global.model.entity.DataStatus;
import peoplegpt.domain.post.model.entity.Category;
import peoplegpt.domain.post.model.entity.Tag;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostDetailResponse {
    private final long postId;
    private final long userId;
    private String title;
    private String content;
    private final Category category;
    private ClassFilter filter;
    private Tag tag;
    private DataStatus status;
    private final LocalDateTime createdAt;

}
