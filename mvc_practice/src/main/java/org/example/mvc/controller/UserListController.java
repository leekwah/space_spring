package org.example.mvc.controller;

import org.example.mvc.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserListController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // users라는 정보도 같이 전달해야한다.
        request.setAttribute("users", UserRepository.findAll());
        return "/user/list"; // 유저의 목록을 return 해주는 것
    }
}
