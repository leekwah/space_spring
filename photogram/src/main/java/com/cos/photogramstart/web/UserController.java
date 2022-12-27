package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model,  @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto", dto);
        return "/user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) { // @@AuthenticationPrincipal 을 통해 Authentication 객체에 바로 접근할 수 있다.
        // 1번 방법 @AuthenticationPrincipal 를 이용하는 것 - 추천
        // System.out.println("세션 정보 : " + principalDetails.getUser()); // session 내의 user 정보를 얻는다.

        /*

        // 2번 방법 직접 SecurityContextHolder 를 통해서 얻는 것 - 쓰는 것 비추천
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
        System.out.println("직접 찾은 세션 정보 : " + mPrincipalDetails.getUser());

        // 세션 정보를 모델에 넣는 방법 -> 원래는 주석처리한 것을 해야하나, <sec:Authorize> 를 통해서 한다.
        model.addAttribute("principal", principalDetails.getUser());

        */
        return "/user/update";
    }
}
