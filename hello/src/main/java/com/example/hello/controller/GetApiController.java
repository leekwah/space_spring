package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController { // 원래 Get을 따로 빼내지는 않으나 실습을 위해서

    @GetMapping(path = "/hello") // http://localhost:9090/api/get/hello
    public String getHello() {
        return "get hello";
    }

    @RequestMapping(value = "/hi", method = RequestMethod.GET) // method = RequestMethod.GET 를 쓰지 않으면, get / post / put / delete 전부가 Mapping 된다.
    public String hi() {
        return "hi";
    }

    // http://localhost:9090/api/get/path-variable/{name} 과 같이 {name} 이 계속 변하는 것
    @GetMapping("/path-variable/{id}")
    public String pathVariable(@PathVariable(name = "id") String pathName){
        System.out.println("PathVariable : " + pathName);
        return pathName;
    }

    // QueryParameter 는 검색시에 매개변수 인자들을 얘기하는 것이다.
    // key 와 value 를 의미한다.
    // ?key=value&key2=value2
    // http://localhost:9090/api/get/query-param?user=kwah&email=kwah.lkh@gmail.com&age=30
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");

        });

        return sb.toString();
    }

    @GetMapping("query-param02") // 명시적인 변수를 받는 방법
    public String queryParam02(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam int age) {

        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name + " " + email +" "+ age;
    }

    @GetMapping("query-param03")// dto 형태로, QueryParam 을 받음, 현업에서 제일 많이 쓰는 방법
    public String queryParam03(UserRequest userRequest) {
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }
}
