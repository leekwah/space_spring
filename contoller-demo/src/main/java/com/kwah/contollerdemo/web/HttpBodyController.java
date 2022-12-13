package com.kwah.contollerdemo.web;

import com.kwah.contollerdemo.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HttpBodyController {

    private static final Logger log = LoggerFactory.getLogger(HttpBodyController.class);

    @PostMapping("/body1")
    public String xwwwformurlencoded(String username) {
        log.info(username);
        return "key=value 전송옴";
    }

    @PostMapping("/body2")
    public String plaintext(@RequestBody String data) {

        log.info(data);
        return "plain/text 전송옴";
    }

    @PostMapping("/body3")
    public String applicationJson(@RequestBody String data) {

        log.info(data); // 만약에 답만 받고 싶을 때는 아래와 같이 하면 된다.


        return "json 전송옴";
    }

    @PostMapping("/body4")
    public String applicationJsonToObject(@RequestBody User user) {

        log.info(user.getUsername());
        return "json 전송옴";
    }
}
