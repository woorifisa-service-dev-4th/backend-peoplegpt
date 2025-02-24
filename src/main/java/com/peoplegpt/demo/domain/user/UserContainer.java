package com.peoplegpt.demo.domain.user;

import com.peoplegpt.demo.domain.user.controller.UserController;

public class UserContainer {

    private static UserController userController;
    
    public static UserController getUserController() {
        return userController;
    }

}
