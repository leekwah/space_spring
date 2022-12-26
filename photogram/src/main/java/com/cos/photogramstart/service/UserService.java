package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true) // SELECT 문의 트랜잭션은 (readOnly = true) 를 건다.
    public User 회원프로필(int userId) {
        // SELECT * FROM image WHERE userId = :userId; 를 JPA 로 이용
        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });
        return userEntity; // 유저의 정보
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
}
