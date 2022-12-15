package com.kwah.contollerdemo.web;

import com.kwah.contollerdemo.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpResponseJsonController {

    @GetMapping("/resp/json")
    public String respJson() {
        return "{\"username\" : \"kwah\"}";
    }

    @GetMapping("/resp/json/object")
    public User respJsonObject() {
        User user = new User();
        user.setUsername("홍길동");
        return user; // 1. Spring 의 MessageConverter 가 자동으로 JavaObject 를 Json 으로 변경해서 통신을 통해 응답해준다.
                     // 2. RestController 일 때만, MessageConverter 가 작동한다.
    }
}
