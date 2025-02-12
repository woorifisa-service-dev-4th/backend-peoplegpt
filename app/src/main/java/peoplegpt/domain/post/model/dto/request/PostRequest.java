package peoplegpt.domain.post.model.dto.request;

import peoplegpt.domain.global.model.entity.ClassFilter;
import peoplegpt.domain.post.model.entity.Category;
import peoplegpt.domain.post.model.entity.Tag;

public class PostRequest {
    private final long userId;
    private final String title;
    private final String content;
    private final Category category;
    private final ClassFilter filter;
    private final Tag tag;

    public PostRequest(long userId, String title, String content, Category category, ClassFilter filter, Tag tag) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.filter = filter;
        this.tag = tag;
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

}