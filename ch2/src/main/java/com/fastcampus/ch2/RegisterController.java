package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URLEncoder;

@Controller // 컨트롤러 어노테이션
public class RegisterController {
    // @RequestMapping("/register/save", method={RequestMethod.GET, RequestMethod.POST}) // 리퀘스트 매핑
    // @RequestMapping("/register/add") // 신규회원 가입 화면
    //@GetMapping("/register/add") // POST 만 사용하기 때문에 -> servlet-context.xml로 view controller로 설정
    // public String register() {
        // return "registerForm"; // WEB-INF/views/registerForm.jsp로 이동한다.
    // }
//    @RequestMapping(value = "/register/save", method = RequestMethod.POST) 길어서 쓰지 않음
    @PostMapping("/regiseter/save") // 4.3 부터 사용가능 위의 RequestMapping 메서드와 같음 // GET 방식만 사용한다.
    public String save(User user, Model m) { // 많기 때문에 user로 받고, model m으로 받는다.
        // 1. 유효성 검사
        if (!isValid(user)) {
            String msg = URLEncoder.encode("id를 잘못입력하셨습니다.");
            // 컨트롤러에서 URL을 인코딩을해지지 않기에 URLEncoder.encode() 메서드를 사용해야한다.
            // 이후 GET으로 받는 registerForm.jsp에서도 decode를 해야한다.
            return "redirect:/register/add?msg="+msg; // GET 방식을 통해서 URL 재작성(rewriting)

        }
        // 2. DB에 신규회원 정보를 저장
        return "registerInfo";
    }

    private boolean isValid(User user) {
        return false;
    }
}
