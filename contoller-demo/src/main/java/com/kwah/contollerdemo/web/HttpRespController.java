package com.kwah.contollerdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 파일을 리턴할 것이기에 @Controller 를 사용한다.
public class HttpRespController {

    @GetMapping("/txt")
    public String txt() {
        return "a.txt"; // Framework 를 사용하고 있기 때문에 틀이 이미 정해져있음 -> 일반 정적 파일들은 resources/static 폴더 내부가 디폴트 경로이다.
    }

    @GetMapping("/mus")
    public String mus() {
        return "b"; // mustache template engine library 등록 -> templates 폴더 안에 .mustache 를 놔두면, 확장자 없이 파일명만 적으면 자동으로 경로를 찾아간다.
    }

    @GetMapping("/jsp")
    public String jsp() {
        return "c"; // src/main/webapp 폴더가 디폴트 경로이다.
    }
}
