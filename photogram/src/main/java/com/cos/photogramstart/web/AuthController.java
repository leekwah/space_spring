package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller // @Controller 의 역할 = IoC, 파일을 리턴하는 컨트롤러
@RequiredArgsConstructor // final 필드를 DI 할 때 사용
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    // public AuthController(AuthService authService) { this.authService = authServices; } @RequiredArgsConstructor 가 없으면 주석 해제

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    // 회원가입 진행 -> /auth/signup -> /auth/signin
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDTO, BindingResult bindingResult) { // key = value 형식으로 온다. (x-www-form-urlencoded 방식)

        if (bindingResult.hasErrors()) { // bindingResult 에 Error 가 있을 경우
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println("==================================================");
                System.out.println(error.getDefaultMessage());
                System.out.println("==================================================");
            }
            // throw new RuntimeException("유효성 검사 실패함");
            throw new CustomValidationException("유효성 검사 실패함", errorMap);
        } else {
            log.info(signupDTO.toString());

            // User 라는 데이터에 <- singupDTO 를 넣는 것이다. (builder 를 활용)
            User user = signupDTO.toEntity(); //
            log.info(user.toString()); // 값이 제대로 넘어오는 지 확인 (다른 user 의 값들은 null 로 처리됨)

            User userEntity = authService.회원가입(user);
            System.out.println(userEntity);

            // 비밀번호 암호화, 권한 설정


            return "auth/signin"; // 회원가입 후 /auth/signin 으로 가게 한다.
            // @ResponseBody 를 넣으면, Data 를 리턴한다. (jsp 파일을 리턴해주지 않음)
        }

    }

}
