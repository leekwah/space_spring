package com.kwah.contollerdemo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryPathController {

    @GetMapping("/chicken")
    public String chickenQuery(String type) {
        return type+" 배달갑니다. (쿼리스트링)";
    }

    @GetMapping("/chicken/{type}") // 가독성은 이게 더 좋다.
    public String chickenPath(@PathVariable String type) {
        return type+" 배달갑니다. (주소변수매핑)";
    }
}
