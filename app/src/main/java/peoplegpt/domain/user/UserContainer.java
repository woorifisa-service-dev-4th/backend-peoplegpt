package peoplegpt.domain.user;

import peoplegpt.domain.user.controller.UserController;
import peoplegpt.domain.user.repository.UserRepository;
import peoplegpt.domain.user.service.UserService;
import peoplegpt.domain.user.service.impl.UserServiceImpl;
import peoplegpt.domain.user.util.UserPasswordSecurity;
import peoplegpt.domain.user.util.impl.UserPasswordSecurityImpl;

public class UserContainer {
    private static UserRepository userRepository = new UserRepository();
    private static final UserPasswordSecurity userPasswordSecurity = new UserPasswordSecurityImpl();
    private static final UserService userService = new UserServiceImpl(userRepository, userPasswordSecurity);
    private static final UserController userController = new UserController(userService);

    public static UserController getUserController() {
        return userController;
    }

}
