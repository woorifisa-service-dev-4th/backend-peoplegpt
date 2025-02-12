package peoplegpt.domain.post.model.dto.request;

public class GetPostListRequest {
    private String category;

    public GetPostListRequest(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
