package com.example.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController // 해당 Class 는 REST API 처리하는 Controller
@RequestMapping("/api") // RequestMapping 은 URI 를 지정해주는 Annotation
public class ApiController {

    @GetMapping("/hello") // http://localhost:9090/api/hello
    public String hello(){
        return "hello spring boot";
    }

    @GetMapping("/text")
    public String text(@RequestParam String account) {
        return account;
    }

    // JSON

}
