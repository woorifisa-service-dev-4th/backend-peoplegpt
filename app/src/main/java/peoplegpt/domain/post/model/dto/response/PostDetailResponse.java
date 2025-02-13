package peoplegpt.domain.post.model.dto.response;

import peoplegpt.domain.global.model.entity.ClassFilter;
import peoplegpt.domain.global.model.entity.DataStatus;
import peoplegpt.domain.post.model.entity.Category;
import peoplegpt.domain.post.model.entity.Tag;

import java.time.LocalDateTime;

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

    public PostDetailResponse(long postId, long userId, String title, String content, Category category, ClassFilter filter, Tag tag, DataStatus status, LocalDateTime createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.filter = filter;
        this.tag = tag;
        this.status = status;
        this.createdAt = createdAt;
    }

    public long getPostId() {
        return postId;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Category getCategory() {
        return category;
    }

    public ClassFilter getFilter() {
        return filter;
    }

    public Tag getTag() {
        return tag;
    }

    public DataStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
