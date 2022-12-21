package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable int id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult, // 꼭 @Valid parameter 다음에 적어야함
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (bindingResult.hasErrors()) { // bindingResult 에 Error 가 있을 경우
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println("==================================================");
                System.out.println(error.getDefaultMessage());
                System.out.println("==================================================");
            }
            throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
        } else { // 정상일 경우에
            User userEntity = userService.회원수정(id, userUpdateDto.toEntity());

            // 이후에 세션 정보를 바꿔야지 적용됨 -> principalDetails 활용
            principalDetails.setUser(userEntity);
            return new CMRespDto<>(1, "회원 수정 완료", userEntity);
        }
    }
}
