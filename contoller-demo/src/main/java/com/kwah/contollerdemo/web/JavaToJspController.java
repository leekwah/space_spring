package com.kwah.contollerdemo.web;

import com.kwah.contollerdemo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JavaToJspController {

    @GetMapping("/jsp/java")
    public String jspToJava() {
        return "d";
    }

    @GetMapping("/jsp/java/model")
    public String jspToJavaToModel(Model model) { // 함수의 Parameter 에 Model 을 선언하고

        User user = new User();
        user.setUsername("kwah");

        model.addAttribute("username", user.getUsername()); // addAttribute 함수로 전달

        return "e";
    }
}
