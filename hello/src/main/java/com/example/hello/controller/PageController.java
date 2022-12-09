package com.example.hello.controller;

import com.example.hello.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    // 페이지를 Return
    @RequestMapping("/main")
    public String main() {
        return "main.html"; // 200 OK 로 html 이 나온다.
    }
    // ResponseEntity
    // json 내리기
    @ResponseBody // 객체 자체를 return 했을 때, ResponseBody 를 내려준다.
    @GetMapping("/users")
    public User user() {
        var user = new User(); // JAVA 11 부터 추가되었다.
        user.setName("kwah");
        user.setAddress("Busan");

        return user;
    }
}
