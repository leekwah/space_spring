package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id, UserUpdateDto userUpdateDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(userUpdateDto);
        User userEntity = userService.회원수정(id, userUpdateDto.toEntity());

        // 이후에 세션 정보를 바꿔야지 적용됨 -> principalDetails 활용
        principalDetails.setUser(userEntity);
        return new CMRespDto<>(1, "회원 수정 완료", userEntity);
    }
}
