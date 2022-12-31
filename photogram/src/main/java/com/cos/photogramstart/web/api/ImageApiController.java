package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.service.LikesService;
import com.cos.photogramstart.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ImageApiController {

    private final ImageService imageService;
    private final LikesService likesService;

    @GetMapping("/api/image")
    public ResponseEntity<?> imageStroy(@AuthenticationPrincipal PrincipalDetails principalDetails, @PageableDefault(size = 3)Pageable pageable) {
        Page<Image> images = imageService.이미지스토리(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(new CMRespDto<>(1, "성공", images), HttpStatus.OK);
    }

    // 좋아요기능
    @PostMapping("/api/image/{imageId}/likes") // 어떤 이미지를 좋아요 하기 때문에, 다음과 같은 주소가 나오게 된다.
    public ResponseEntity<?> likes(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int imageId) {
        likesService.좋아요(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1, "좋아요 성공", null), HttpStatus.CREATED);
    }
    // 좋아요 취소
    @DeleteMapping("/api/image/{imageId}/likes") // 어떤 이미지를 좋아요 하기 때문에, 다음과 같은 주소가 나오게 된다.
    public ResponseEntity<?> unLikes(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int imageId) {
        likesService.좋아요취소(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1, "좋아요취소 성공", null), HttpStatus.OK);
    }

}
