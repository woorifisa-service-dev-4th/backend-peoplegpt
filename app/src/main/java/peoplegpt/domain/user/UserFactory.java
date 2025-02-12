package peoplegpt.domain.user;

import peoplegpt.domain.user.controller.UserController;
import peoplegpt.domain.user.repository.UserRepository;
import peoplegpt.domain.user.service.UserService;
import peoplegpt.domain.user.service.impl.UserServiceImpl;

public class UserFactory {
    private static UserRepository userRepository = new UserRepository();
    private static final UserService userService = new UserServiceImpl(userRepository);
    private static final UserController userController = new UserController(userService);

    public static UserController getUserController() {
        return userController;
    }

}
