package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User 회원수정(int id, User user) {
        // 1. 영속화
        User userEntity = userRepository.findById(id).get();
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
