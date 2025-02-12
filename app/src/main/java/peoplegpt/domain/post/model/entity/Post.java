package peoplegpt.domain.post.model.entity;

import peoplegpt.domain.global.model.entity.ClassFilter;
import peoplegpt.domain.global.model.entity.DataStatus;

import java.time.LocalDateTime;

public class Post {
    private long postId;
    private long userId;
    private String title;
    private String content;
    private Category category;
    private ClassFilter filter;
    private Tag tag;
    private DataStatus status;
    private LocalDateTime createdAt;
    public Post(int postId, long userId, String title, String content, Category category, ClassFilter filter, Tag tag, DataStatus status, LocalDateTime createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.filter = filter;
        this.tag = tag;
        this.status = DataStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
    }

    // 게시물 작성시 사용
    public Post(long userId, String title, String content, Category category, ClassFilter filter, Tag tag) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.filter = filter;
        this.tag = tag;
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
