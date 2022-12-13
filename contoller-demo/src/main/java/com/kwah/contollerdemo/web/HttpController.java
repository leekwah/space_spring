package com.kwah.contollerdemo.web;

import org.springframework.web.bind.annotation.*;

@RestController // Data 를 응답하는 컨트롤러 @Controller 는 File 을 응답하는 컨트롤러
public class HttpController {

    // http://localhost:8080/get
    @GetMapping("/get")
    public String get() {
        return "<h1>get 요청됨</h1>";
    }

    // http://localhost:8080/post
    @PostMapping("/post")
    public String post() {
        return "post 요청됨";
    }

    // http://localhost:8080/put
    @PutMapping("/put")
    public String put() {
        return "put 요청됨";
    }

    // http://localhost:8080/delete
    @DeleteMapping("/delete")
    public String delete() {
        return "delete 요청됨";
    }
}
