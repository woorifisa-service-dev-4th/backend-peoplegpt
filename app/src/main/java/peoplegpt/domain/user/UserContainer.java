package peoplegpt.domain.user;

import peoplegpt.domain.user.controller.UserController;

public class UserContainer {

    private static final UserController userController = new UserController();

    public static UserController getUserController() {
        return userController;
    }

}
