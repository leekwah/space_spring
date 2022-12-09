package com.example.hello;

import com.example.hello.dto.Member;
import com.example.hello.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("--------------------");
        // Object Mapper 는 Text JSON -> Object 으로 바꾸는 역할을 하거나 Object -> Text JSON 으로 바꾸는 역할을 한다.

        var objectMapper = new ObjectMapper();

        // object -> text
        // object mapper 가 get method 를 활용한다.
        var member = new Member("kwah", 30);
        var text = objectMapper.writeValueAsString(member);
        System.out.println(text);

        // text -> object
        // object mapper 가 기본 생성자를 활용한다. (get 이 들어가면 안된다.)
        var objectMember = objectMapper.readValue(text, Member.class);
        System.out.println(objectMember);

        // user로 해보기
        var user = new User("kwah", 30, "010-1234-5678","Busan");
        var info = objectMapper.writeValueAsString(user);
        System.out.println(info);

        var objectUser = objectMapper.readValue(info, User.class);
        System.out.println(objectUser);

    }

}
