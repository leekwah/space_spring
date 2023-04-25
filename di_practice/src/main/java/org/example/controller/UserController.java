package org.example.controller;

import org.example.annotation.Controller;
import org.example.annotation.Inject;
import org.example.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    @Inject // 생성자에 @Inject 를 붙인다.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}
