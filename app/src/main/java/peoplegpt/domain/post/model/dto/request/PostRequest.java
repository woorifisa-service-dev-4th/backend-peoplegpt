package peoplegpt.domain.post.model.dto.request;

import peoplegpt.domain.global.model.entity.ClassFilter;
import peoplegpt.domain.post.model.entity.Category;
import peoplegpt.domain.post.model.entity.Tag;

public class PostRequest {
    private long userId;
    private String title;
    private String content;
    private Category category;
    private ClassFilter filter;
    private Tag tag;

    public PostRequest(long userId, String title, String content, Category category, ClassFilter filter, Tag tag) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.filter = filter;
        this.tag = tag;
    }
}