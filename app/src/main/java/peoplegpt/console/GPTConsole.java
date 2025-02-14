package peoplegpt.console;

import java.util.List;
import java.util.Scanner;

import peoplegpt.domain.comment.CommentContainer;
import peoplegpt.domain.comment.controller.CommentController;
import peoplegpt.domain.comment.model.dto.request.CreateCommentRequest;
import peoplegpt.domain.comment.model.dto.request.GetCommentListRequest;
import peoplegpt.domain.comment.model.dto.response.CreateCommentResponse;
import peoplegpt.domain.comment.model.dto.response.GetCommentListResponse;
import static peoplegpt.domain.global.model.entity.DataStatus.ACTIVE;
import peoplegpt.domain.post.PostContainer;
import peoplegpt.domain.post.controller.PostController;
import peoplegpt.domain.post.model.dto.response.PostDetailResponse;
import peoplegpt.domain.post.model.dto.response.PostListResponse;
import peoplegpt.domain.post.model.entity.Post;
import peoplegpt.domain.user.UserContainer;
import peoplegpt.domain.user.controller.UserController;
import peoplegpt.domain.user.model.dto.request.GetUserRequest;
import peoplegpt.domain.user.model.dto.request.SignInRequest;
import peoplegpt.domain.user.model.dto.request.SignUpRequest;
import peoplegpt.domain.user.model.dto.response.SignResponse;
import peoplegpt.domain.user.model.dto.response.UserResponse;

public class GPTConsole {

    private static final String[] titles = {"사람 기반 AI 인척 답변", "실시간 분석", "맞춤형 솔루션"};
    private static final String[] descriptions = {
        "실제 사람들이 질문에 답변 해드립니다",
        "인적 자원 기반의 실시간 질문 분석을 제공합니다",
        "강사님의 맞춤형 일일 강의 노트를 제공합니다"
    };

    private static Scanner scanner = new Scanner(System.in);
    private static UserContainer userContainer = new UserContainer();
    private static PostContainer postContainer = new PostContainer();
    private static CommentContainer commentContainer = new CommentContainer();
    private static final UserController userController = userContainer.getUserController();
    private static final PostController postController = postContainer.getPostController();
    private static final CommentController commentController = commentContainer.getCommentController();
    
    private static void printHello() {
        System.out.println("==============================================================");
        System.err.println("=                                                            =");
        System.out.println("=   ===== ===== ====== ===== =     ===== ===== ===== =====   =");
        System.out.println("=   =   = =     =    = =   = =     =     =   = =   =   =     =");
        System.out.println("=   =   = =     =    = =   = =     =     =   = =   =   =     =");
        System.out.println("=   =   = =     =    = =   = =     =     =     =   =   =     =");
        System.out.println("=   =   = =     =    = =   = =     =     =     =   =   =     =");
        System.out.println("=   ===== ===== =    = ===== =     ===== =     =====   =     ="); //
        System.out.println("=   =     =     =    = =     =     =     = === =       =     =");
        System.out.println("=   =     =     =    = =     =     =     = = = =       =     =");
        System.out.println("=   =     =     =    = =     =     =     =   = =       =     =");
        System.out.println("=   =     =     =    = =     =     =     =   = =       =     =");
        System.out.println("=   =     ===== ====== =     ===== ===== ===== =       =     =");
        System.err.println("=                                                            =");
        System.out.println("==============================================================");
    }

    private static void printClean() {
        System.out.print("\033[H\033[2J"); // 콘솔 초기화
        System.out.flush(); // 출력 버퍼 비우기
    }

    public static void run() {
        boolean systemOn = true;

        while(systemOn) {
            System.out.println("People GPT Console version에 오신 것을 환영합니다.");
            System.out.println("우리 서비스는 다음 3가지를 보장합니다.\n");
            System.out.println("=====================================");
            for(int i = 0; i < titles.length; i++) {
                System.out.println((i + 1) + ". " + titles[i]);
                System.out.println(descriptions[i]);
            }
            System.out.println("=====================================\n\n");

            printHello();

            systemOn = indexPage();
            systemOn = mainPage();
        }
    }

    private static boolean  indexPage() {
        boolean systemOn = true;

        while(systemOn) {
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 종료");
            System.out.println("4. 화면 정리");
            try {
                int selected = Integer.parseInt(scanner.nextLine());
                switch(selected) {
                    case 1 -> {
                        printClean();
                        systemOn = loginPage();
                    }
                    case 2 -> registerPage();
                    case 3 -> { 
                        return false;
                    }
                    case 4 -> printClean();
                    default -> System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            } catch(NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

        return true;
    }

    private static boolean loginPage() {

        // check data is can
        System.out.println("로그인 페이지입니다.");
        System.out.println("이메일을 입력해주세요: ");
        String email = scanner.nextLine();
        System.out.println("비밀번호를 입력해주세요: ");
        String password = scanner.nextLine();

        // 로그인 요청
        SignInRequest request = new SignInRequest(email, password);
        SignResponse response = (SignResponse) userController.signInUser(request);
        
        if(response.getEmail() != null) {
            System.out.println(response.getEmail() + "님 환영합니다.");
            return false;
        }
        return true;
    }

    private static void registerPage() {
        System.out.println("회원가입 페이지입니다.");
        System.out.println("이름을 입력해주세요: ");
        String name = scanner.nextLine();
        System.out.println("이메일을 입력해주세요: ");
        String email = scanner.nextLine();
        System.out.println("비밀번호를 입력해주세요: ");
        String password = scanner.nextLine();

        // 회원가입 요청
        SignUpRequest request = new SignUpRequest(email, password, name);
        SignResponse response = (SignResponse) userController.signUpUser(request);
        
        if(response.getEmail() != null) {
            System.out.println(response.getEmail() + "님 환영합니다. 회원가입이 완료되었습니다.");
        }
    }

    private static boolean postPage(String category) {
        PostListResponse response = postController.displayPostsByCategory(category);
        List<Post> posts = response.getPosts();

        for(int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            System.out.println(post.getTitle());
            System.out.println(post.getContent());
            System.out.println(post.getCreatedAt());
        }
        return false;
    }

    private static boolean commentPage(long postId) {
        boolean systemOn = true;

        while(systemOn) {
            postDetailPage();   
            System.out.println("1. 댓글 조회");
            System.out.println("2. 댓글 작성");
            System.out.println("3. 댓글 수정");
            System.out.println("4. 댓글 삭제");
            System.out.println("5. 종료");

            try {
                int selected = Integer.parseInt(scanner.nextLine());
                switch(selected) {
                    case 1 -> {
                        GetCommentListRequest request = new GetCommentListRequest(postId);
                        GetCommentListResponse response = commentController.getCommentList(request);
                        response.getComments().forEach(comment -> {
                            System.out.println(comment.getContent());
                        });
                    }
                    case 2 -> {
                        CreateCommentRequest request = new CreateCommentRequest(1, (int)postId, "댓글 내용");
                        commentController.createComment(request);
                        CreateCommentResponse response = commentController.createComment(request);
                        if(response.getCommentId() != -1) {
                            System.out.println("댓글이 생성되었습니다.");
                        } else {
                            System.out.println("댓글 생성에 실패했습니다.");
                        }

                    }
                    case 5 -> {
                        systemOn = false;
                    }
                    default -> System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            } catch(NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
        return false;
    }

    private static void postDetailPage() {
        PostDetailResponse response = postController.displayPostByPostId(1);

        if (response.getStatus() == ACTIVE) {
            System.out.println(response.getPostId());
            System.out.println(response.getTitle());
            System.out.println(response.getContent());
            System.out.println(response.getCreatedAt());
            System.out.println(response.getCategory());
            System.out.println(response.getFilter());
            System.out.println(response.getTag());
        }
    }

    private static boolean  mainPage() {
        boolean systemOn = true;
        int category = 1; // default 1 = QnA
        int currentFilter = 0; // default 0 = All
        System.out.println("메인 페이지입니다.");

        while(systemOn) {
            System.out.println("1. 회원정보 조회");
            System.out.println("2. QnA 게시글 조회");
            System.out.println("3. CodeShare 게시글 조회");
            System.out.println("4. Daily Summary 게시글 조회");
            System.out.println("5. QnA 게시글 댓글 확인");
            System.out.println("6. 게시글 작성");
            System.out.println("7. 게시글 수정");
            System.out.println("8. 게시글 삭제");
            System.out.println("9. 종료");


            try {
                int selected = Integer.parseInt(scanner.nextLine());
                switch(selected) {
                    case 1 -> {
                        GetUserRequest request = new GetUserRequest("chans");
                        UserResponse response = userController.getUserInfo(request);
                        System.out.println(response.getUserId());
                        System.out.println(response.getName());
                        System.out.println(response.getEmail());
                        System.out.println(response.getRole());
                        System.out.println(response.getCreatedAt());
                    }
                    case 2 -> {
                        systemOn = postPage("QNA");
                    }
                    case 3 -> {
                        systemOn = postPage("CODESHARE");
                    }
                    case 4 -> {
                        systemOn = postPage("DAILY");
                    }
                    case 5 -> {
                        commentPage(1);
                    }
                    case 9 -> systemOn = false;
                    default -> System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            } catch(NumberFormatException e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }

        return false;
    }
}