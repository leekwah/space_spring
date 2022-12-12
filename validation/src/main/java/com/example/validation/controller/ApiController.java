package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/user")
    public Object user(@Valid @RequestBody User user, BindingResult bindingResult) { // @Valid 어노테이션을 통해서, 형식에 맞는지 확인한다.
        // BindingResult 는 에러가 뜨더라도, 값이 나오게 한다.

        if (bindingResult.hasErrors()) { // bindingResult 은 에러를 가지고 있으면 메세지를 보냄 (새로운 변환값을 받을 수 있다.)
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field : " + field.getField());
                System.out.println(message);

                sb.append("field : " + field.getField());
                sb.append("message : " + message);
            });

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString()); // body 에 값을 뿌림
        }

        System.out.println(user);

        return user;
    }
}
