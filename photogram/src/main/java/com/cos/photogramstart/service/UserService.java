package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${file.path}") // application.yml 에 적은 것 -> 추후에 폴더를 변경할 때도 편함 (내가 만든 키값)
    private String uploadFolder; // 업로드 폴더의 위치

    @Transactional(readOnly = true) // SELECT 문의 트랜잭션은 (readOnly = true) 를 건다.
    public UserProfileDto 회원프로필(int pageUserId, int principalId) { // pageUserId 는 해당 페이지의 아이디, principalId 는 로그인한 사용자 아이디
        UserProfileDto dto = new UserProfileDto();

        // SELECT * FROM image WHERE userId = :userId; 를 JPA 로 이용
        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });

        dto.setUser(userEntity);
        dto.setImageCount(userEntity.getImages().size());
        dto.setPageOwnerState(pageUserId == principalId); // true 면 주인, false 면 아니다.

        int subscribeState = subscribeRepository.mSebscribeState(principalId, pageUserId);
        int subscribeCount = subscribeRepository.mSubScribeCount(pageUserId);

        dto.setSubscribeState(subscribeState == 1);
        dto.setSubscribeCount(subscribeCount);

        // 프로필에 나오는 사진 좋아요 개수 추가하는 첫번째 방법 (profile.jsp 에도 두번째 방법이 있음)
        userEntity.getImages().forEach((image) -> {
            image.setLikeCount(image.getLikes().size());
        });


        return dto; // 유저의 정보
    }

    @Transactional
    public User 회원수정(int id, User user) {
        // 1. 영속화
        // id 가 있는 경우
        // User userEntity = userRepository.findById(id).get();

        // id 가 없는 경우에 예외 처리
        User userEntity = userRepository.findById(id).orElseThrow(() -> {return new CustomValidationApiException("찾을 수 없는 id 입니다.");});
        // Optional
        // (1) 무조건 찾음 -> get()
        // (2) 못찾으면 Exception 발동시킴 -> orElseThrow

        // 2. 영속화 된 오브젝트 수정 - 더티쳌팅 (업데이트 완료) - DB 반영
        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword); // 비밀번호 암호화 필요함 -> 아니면 같은 걸로 로그인이 안됨
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    }

    @Transactional
    public User 회원프로필사진변경(int principalId, MultipartFile profileImageFile) {
        // 파일을 구분하기 위해서 UUID 를 사용한다.
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid+"_"+profileImageFile.getOriginalFilename(); // 실제 파일 이름이 들어가게 된다. ex) 1.jpg 를 넣으면 1.jpg 가 들어간다.
        // UUID 와 파일명을 섞으면, 진짜 만에 하나 겹칠 이름을

        Path imagefilePath = Paths.get(uploadFolder + imageFileName);

        // 통신, I/O -> 예외 발생 가능성이 있음 (컴파일 시에 발견 불가능, 런타임 시에만 발견 가능함)
        try {
            Files.write(imagefilePath,profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // DB 에 수정된 걸 저장
        User userEntity = userRepository.findById(principalId).orElseThrow(() -> {
            throw new CustomApiException("유저를 찾을 수 없습니다.");
        });

        userEntity.setProfileImageUrl(imageFileName);

        return userEntity; // 세션에 값을 저장해야하기 때문에
    } // 더티체킹으로 업데이트 됨
}
