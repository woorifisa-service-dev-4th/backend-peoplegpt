package peoplegpt.domain.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import peoplegpt.domain.global.model.entity.ClassFilter;
import peoplegpt.domain.global.model.entity.DataStatus;
import peoplegpt.domain.post.model.dto.response.PostDetailResponse;
import peoplegpt.domain.post.model.dto.response.PostListResponse;
import peoplegpt.domain.post.model.entity.Category;
import peoplegpt.domain.post.model.entity.Post;
import peoplegpt.domain.post.model.entity.Tag;
import peoplegpt.domain.post.repository.PostRepository;
import peoplegpt.domain.post.service.PostService;
import peoplegpt.domain.post.service.impl.PostServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostServiceTest {

    private PostService postService;
    private PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        this.postRepository = new PostRepository();
        this.postService = new PostServiceImpl(postRepository);
    }

    @Test
    @DisplayName("게시글 목록 조회 테스트")
    void should_Return_Post_List() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1, 1, "Spring Security를 활용한 OAuth2 인증 구현", "OAuth2를 적용하여 보안성을 강화하는 방법을 공유합니다.", Category.QNA, ClassFilter.AI, Tag.PROJECT, DataStatus.ACTIVE, LocalDateTime.parse("2025-02-11T10:00:00")));
        posts.add(new Post(2, 2, "AWS Lambda와 API Gateway를 활용한 서버리스 아키텍처", "서버리스 환경에서 API를 구축하는 방법을 설명합니다.", Category.QNA, ClassFilter.CLOUD, Tag.STUDY, DataStatus.ACTIVE, LocalDateTime.parse("2025-03-15T14:30:00")));
        posts.add(new Post(3, 3, "GPT API를 활용한 챗봇 서비스 개발", "OpenAI의 GPT API를 활용하여 챗봇을 개발하는 과정과 주요 개념을 다룹니다.", Category.QNA, ClassFilter.SERVICE, Tag.LANGUAGE, DataStatus.ACTIVE, LocalDateTime.parse("2025-04-20T09:45:00")));
        posts.add(new Post(4, 4, "대규모 트래픽을 고려한 MSA 설계 방법", "MSA 구조에서 트래픽 분산 및 성능 최적화 방안을 다룹니다.", Category.QNA, ClassFilter.CLOUD, Tag.ETC, DataStatus.ACTIVE, LocalDateTime.parse("2025-05-05T16:20:00")));
        posts.add(new Post(5, 5, "Spring Batch를 활용한 대량 데이터 처리", "Spring Batch를 이용하여 효율적으로 대용량 데이터를 처리하는 방법을 소개합니다.", Category.QNA, ClassFilter.AI, Tag.STUDY, DataStatus.ACTIVE, LocalDateTime.parse("2025-06-25T11:10:00")));

        // 정렬 추가 (postId 기준 오름차순)
        posts.sort(Comparator.comparing(Post::getPostId));

        String category = "QNA";
        PostListResponse postListResponses = postService.getPostsByCategory(category);

        // 반환된 리스트도 정렬
        List<Post> responsePosts = postListResponses.getPosts();
        responsePosts.sort(Comparator.comparing(Post::getPostId));

        // 리스트 크기 비교
        assertEquals(posts.size(), responsePosts.size(), "리스트 크기가 다릅니다.");

        // 요소 비교
        for (int i = 0; i < posts.size(); i++) {
            Post post = responsePosts.get(i);
            Post expected = posts.get(i);

            assertAll(
                    () -> assertEquals(expected.getPostId(), post.getPostId()),
                    () -> assertEquals(expected.getUserId(), post.getUserId()),
                    () -> assertEquals(expected.getTitle(), post.getTitle()),
                    () -> assertEquals(expected.getContent(), post.getContent()),
                    () -> assertEquals(expected.getCategory(), post.getCategory()),
                    () -> assertEquals(expected.getFilter(), post.getFilter()),
                    () -> assertEquals(expected.getTag(), post.getTag()),
                    () -> assertEquals(expected.getStatus(), post.getStatus()),
                    () -> assertEquals(expected.getCreatedAt(), post.getCreatedAt())
            );
        }
    }


    @Test
    @DisplayName("게시글 상세 조회 테스트")
    void should_Return_Post_Detail() {
        int postId = 1;
        PostDetailResponse postDetailResponse = postService.getPostByPostId(postId);

        PostDetailResponse expected = new PostDetailResponse(1, 1, "Spring Security를 활용한 OAuth2 인증 구현", "OAuth2를 적용하여 보안성을 강화하는 방법을 공유합니다.", Category.QNA, ClassFilter.AI, Tag.PROJECT, DataStatus.ACTIVE, LocalDateTime.parse("2025-02-11T10:00:00"));

        assertAll(
                () -> assertEquals(expected.getPostId(), postDetailResponse.getPostId(), "postID가 일치하지 않습니다."),
                () -> assertEquals(expected.getUserId(), postDetailResponse.getUserId(), "userID가 일치하지 않습니다."),
                () -> assertEquals(expected.getTitle(), postDetailResponse.getTitle(), "제목이 일치하지 않습니다."),
                () -> assertEquals(expected.getContent(), postDetailResponse.getContent(), "내용이 일치하지 않습니다."),
                () -> assertEquals(expected.getCategory(), postDetailResponse.getCategory(), "카테고리가 일치하지 않습니다."),
                () -> assertEquals(expected.getFilter(), postDetailResponse.getFilter(), "필터가 일치하지 않습니다."),
                () -> assertEquals(expected.getTag(), postDetailResponse.getTag(), "태그가 일치하지 않습니다."),
                () -> assertEquals(expected.getStatus(), postDetailResponse.getStatus(), "상태가 일치하지 않습니다."),
                () -> assertEquals(expected.getCreatedAt(), postDetailResponse.getCreatedAt(), "생성 시간이 일치하지 않습니다.")
        );
    }
}
