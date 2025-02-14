package peoplegpt.domain.post.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import peoplegpt.domain.global.model.entity.ClassFilter;
import peoplegpt.domain.global.model.entity.DataStatus;
import peoplegpt.domain.post.model.entity.Category;
import peoplegpt.domain.post.model.entity.Post;
import peoplegpt.domain.post.model.entity.Tag;

public class PostRepository {
    private static final Logger logger = LogManager.getLogger(PostRepository.class);
    private final String POST_DATA_FILE = "post_data.txt";

    private List<Post> posts = parsePostData();

    private List<Post> parsePostData() {
        List<Post> result = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();

        try (
            InputStream inputStream = classLoader.getResourceAsStream(POST_DATA_FILE);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))
        ) {
            if (inputStream == null) {
                throw new IOException("Resource file not found: " + POST_DATA_FILE);
            }

            String line;
            while ((line = br.readLine()) != null) {
                Post post;
                String[] postDetails = line.split(",");
                long postId = Long.parseLong(postDetails[0]);
                long userId = Long.parseLong(postDetails[1]);
                String title = postDetails[2];
                String content = postDetails[3];
                Category category = Category.valueOf(postDetails[4]);
                if(category == Category.QNA) {
                    ClassFilter filter = ClassFilter.valueOf(postDetails[5].toUpperCase(Locale.ROOT));
                    Tag tag = Tag.valueOf(postDetails[6].toUpperCase(Locale.ROOT));
                    DataStatus status = DataStatus.valueOf(postDetails[7].toUpperCase(Locale.ROOT));
                    LocalDateTime createdAt = LocalDateTime.parse(postDetails[8]);
                    post = new Post(postId, userId, title, content, category, filter, tag, status, createdAt);
                } else {
                    DataStatus status = DataStatus.valueOf(postDetails[5].toUpperCase(Locale.ROOT));
                    LocalDateTime createdAt = LocalDateTime.parse(postDetails[6]);
                    post = new Post(postId, userId, title, content, category, status, createdAt);
                }
                result.add(post);
            }
        } catch (IOException e) {
            logger.error("게시물 데이터 파일을 읽어오는데 실패했습니다.",e);
            throw new RuntimeException("게시물 데이터 파일을 읽어오는데 실패했습니다.");
        }
        return result;
    }

    public List<Post> getPosts() {
        List<Post> result = new ArrayList<>();
        for (Post post : posts) {
            if (post.getStatus() == DataStatus.ACTIVE) {
                result.add(post);
            }
        }
        result.sort((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()));
        return result;
    }

    // 게시글 목록 조회
    public List<Post> getPostsByCategory(Category category) {
        List<Post> result = new ArrayList<>();
        for (Post post : posts) {
            if (post.getCategory().equals(category)) {
                result.add(post);
            }
        }
        result.sort((p1, p2) -> p2.getCreatedAt().compareTo(p1.getCreatedAt()));
        return result;
    }

    // 게시글 상세 조회
    public Post findPostByPostId(long postId) {
        return posts.stream()
                .filter(post -> post.getPostId() == postId)
                .findFirst()
                .orElse(null);
    }

    public long generatePostId() {
        return posts.stream()
                .mapToLong(Post::getPostId)
                .max()
                .orElse(0) + 1;
    }
}


