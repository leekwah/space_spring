package com.example.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base74Encoder") // @Component 를 적으면 객체로 관리된다. 이후에 ""를 통해서 이름을 적을 수 있다.
public class Base64Encoder implements IEncoder { // 객체로 관리해야한다.
    @Override
    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
