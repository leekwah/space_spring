package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}") // application.yml 에 적은 것 -> 추후에 폴더를 변경할 때도 편함 (내가 만든 키값)
    private String uploadFolder; // 업로드 폴더의 위치

    @Transactional // 추후에 여러가지 INSERT 나 UPDATE 가 섞여있을 경우에 두 가지 로직이 하나의 서비스가 되기에 습관이 되어야한다. ( 일의 최소단위 = 트랜잭션)
    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        // 파일을 구분하기 위해서 UUID 를 사용한다.
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); // 실제 파일 이름이 들어가게 된다. ex) 1.jpg 를 넣으면 1.jpg 가 들어간다.
        // UUID 와 파일명을 섞으면, 진짜 만에 하나 겹칠 이름을

        Path imagefilePath = Paths.get(uploadFolder + imageFileName);

        // 통신, I/O -> 예외 발생 가능성이 있음 (컴파일 시에 발견 불가능, 런타임 시에만 발견 가능함)
        try {
            Files.write(imagefilePath,imageUploadDto.getFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 이미지 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);
    }

    @Transactional(readOnly = true)
    public Page<Image> 이미지스토리 (int principalId, Pageable pageable) {
        Page<Image> images = imageRepository.mStory(principalId, pageable);
        return images;
    }
}
