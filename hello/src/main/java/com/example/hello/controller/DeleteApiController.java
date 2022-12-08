package com.example.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete/{userId}") // Get 과 동일하다. Resource 가 없어도 ERROR 가 아닌 200 OK를 보냄
    public void delete(@PathVariable String userId, @RequestParam String account){ // DELETE 는 많은 값을 받지 않음
        System.out.println(userId);
        System.out.println(account);
    }
}
