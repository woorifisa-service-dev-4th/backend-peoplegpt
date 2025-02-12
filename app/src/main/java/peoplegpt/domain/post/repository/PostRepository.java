package peoplegpt.domain.post.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import peoplegpt.domain.global.model.entity.ClassFilter;
import peoplegpt.domain.post.model.entity.Category;
import peoplegpt.domain.post.model.entity.Post;
import peoplegpt.domain.post.model.entity.Tag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    private final String POST_FILE_PATH = "main/resources/post_data.txt";
    private static final Logger logger = LogManager.getLogger(PostRepository.class);

    private List<Post> posts = parsePostData();

    private List<Post> parsePostData() {
        List<Post> posts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(POST_FILE_PATH))){
            String line;
            while ((line = br.readLine()) != null) {
                String[] postDetails = line.split(",");
                long userId = Long.parseLong(postDetails[0]);
                String title = postDetails[1];
                String content = postDetails[2];
                Category category = Category.valueOf(postDetails[3]);
                ClassFilter filter = ClassFilter.valueOf(postDetails[4]);
                Tag tag = Tag.valueOf(postDetails[5]);
                Post post = new Post(userId, title, content, category, filter, tag);
                posts.add(post);
            }
        } catch (IOException e) {
            logger.error("게시물 데이터 파일을 읽어오는데 실패했습니다.",e);
            throw new RuntimeException("게시물 데이터 파일을 읽어오는데 실패했습니다.");
        }
        return posts;
    }

    // 게시글 목록 조회
    public List<Post> getPostsByCategory(String category) {
        List<Post> result = new ArrayList<>();
        for (Post post : posts) {
            if (post.getCategory().equals(category)) {
                result.add(post);
            }
        }
        return result;
    }
}


