package com.example.hello.controller;

import com.example.hello.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    // user 객체를 req -> object mapper 를 통해서 -> object 로 바뀜 이후에 -> method 를 타고 -> object -> object mapper -> json -> response 로 간다.
    @PostMapping("/json")
    public User json(@RequestBody User user) {
        return user; // 200 OK
    }

    // ResponseEntity 를 통해서 명확하게 응답을 내릴 수 있는 것
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user) {
        // 생성되었을 때 201 을 내린다. (수정되었을 때는 200)
        return ResponseEntity.status(HttpStatus.CREATED).body(user); // body 이외엔 header 값도 추가할 수 있음
        // ResponseEntity 를 통해서 명확한 값을 만든 뒤에 추가하는 것이 좋음
    }
}
