package peoplegpt.domain.user;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import peoplegpt.domain.user.model.dto.request.GetUserRequest;
import peoplegpt.domain.user.model.dto.request.SignInRequest;
import peoplegpt.domain.user.model.dto.response.SignResponse;
import peoplegpt.domain.user.model.dto.response.UserResponse;
import peoplegpt.domain.user.model.entity.UserRole;
import peoplegpt.domain.user.repository.UserRepository;
import peoplegpt.domain.user.service.UserService;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName("Test sign in")
    public void testJunit() {
        System.out.println("Test Junit in");
        assertTrue(true);
    }

    @Test
    @Disabled
    @DisplayName("Test get user")
    public void testGetUser() {
        GetUserRequest getUserRequest = new GetUserRequest("test@woorifisa.com");
        UserResponse user = userService.getUser(getUserRequest);
        UserResponse expected = new UserResponse(1, "test@woorifisa.com", "tester", UserRole.ADMIN, LocalDateTime.parse("2025-02-11T10:00:00"));
        System.out.println("User: " + user.getUserId() + " " + user.getEmail() + " " + user.getName() + " " + user.getRole() + " " + user.getCreatedAt());
        assertAll(
            () -> assertTrue(user.getUserId() == expected.getUserId(), "User Id is not equal"),
            () -> assertTrue(user.getEmail().equals(expected.getEmail()), "Email is not equal"),
            () -> assertTrue(user.getName().equals(expected.getName()), "Name is not equal"),
            () -> assertTrue(user.getRole().equals(expected.getRole()), "Role is not equal"),
            () -> assertTrue(user.getCreatedAt().equals(expected.getCreatedAt()), "Created at is not equal")
        );
    }

    @Test
    @Disabled
    @DisplayName("Test Sign in")
    public void testSignIn() {
        SignInRequest signInRequest = new SignInRequest("test@woorifisa.com", "1234");
        SignResponse siginInresponse = userService.signIn(signInRequest);
        SignResponse expected = new SignResponse("test@woorifisa.com");
        
        System.out.println("Sign in response: " + siginInresponse.getEmail());

        assertAll(
            () -> assertTrue(siginInresponse.getEmail().equals(expected.getEmail()), "Email is not equal")
        );
    }
}
