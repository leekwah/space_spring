package com.example.hello.controller;

import com.example.hello.dto.PostRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostApiController {
    @PostMapping("/post")
    public void post(@RequestBody PostRequestDTO requestData) { // POST 는 RequestBody를 사용한다.
        System.out.println(requestData);
    }
}
