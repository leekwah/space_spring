package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/", "/image/story"})
    public String story(){
        return "/image/story";
    }
    @GetMapping("/image/popular")
    public String popular(){
        return "/image/popular";
    }

    @GetMapping("/image/upload")
    public String upload() {
        return "/image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) { // 파일과 유저 정보가 필요하기 때문에

        if (imageUploadDto.getFile().isEmpty()) { // imageFile 이 아무것도 업로드 되지 않았을 때
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null); // 페이지를 응답할 것이기 때문에
        }


        // 이후에 서비스를 호출하고, return -> 본인의 페이지로 (redirect 사용)
        imageService.사진업로드(imageUploadDto, principalDetails);
        return "redirect:/user/"+principalDetails.getUser().getId();
    }
}
