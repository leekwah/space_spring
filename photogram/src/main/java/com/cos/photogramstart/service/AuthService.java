package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // @Service 어노테이션의 역할 = IoC 등록, 트랜잭션 관리
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional // Write (Insert, Update, Delete) 할 때 트랜잭션을 사용한다.
    public User 회원가입(User user) {
        // 회원가입을 진행 -> 레파지토리가 필요하다. (UserRepository)

        // 비밀번호 암호화
        String rawPassword = user.getPassword(); // 원래 기본 패스워드
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword); // 암호화 된 패스워드 활용
        user.setRole("ROLE_USER"); // 기본 권한을 주는 것 (user), 추후에 관리자에는 ROLE_ADMIN 을 줄 것이다.

        User userEntity = userRepository.save(user); // 넣은 타입을 리턴한다.
        return userEntity;
    }
}
