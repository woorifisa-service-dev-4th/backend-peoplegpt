package peoplegpt.domain.user;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import peoplegpt.domain.user.model.dto.request.GetUserRequest;
import peoplegpt.domain.user.model.dto.response.UserResponse;
import peoplegpt.domain.user.repository.UserRepository;
import peoplegpt.domain.user.service.UserService;
import peoplegpt.domain.user.service.impl.UserServiceImpl;
import peoplegpt.domain.user.util.UserPasswordSecurity;
import peoplegpt.domain.user.util.impl.UserPasswordSecurityImpl;

public class UserServiceTest {
    private UserPasswordSecurity userPasswordSecurity;
    private UserRepository userRepository;
    private UserService userService;
    @BeforeEach
    public void setUp() {
        this.userPasswordSecurity = new UserPasswordSecurityImpl();
        this.userRepository = new UserRepository();
        this.userService = new UserServiceImpl(userRepository, userPasswordSecurity);
        System.out.println("Setting up");
    }

    @Test
    @DisplayName("Test sign in")
    public void testSignIn() {
        System.out.println("Test sign in");
        assertTrue(true);
    }

    @Test
    @DisplayName("Test get user")
    public void testGetUser() {
        GetUserRequest getUserRequest = new GetUserRequest("john@woorifisa.com");
        UserResponse user = userService.getUser(getUserRequest);
        System.out.println("Test get user");
        assertTrue(true);
    }
}
