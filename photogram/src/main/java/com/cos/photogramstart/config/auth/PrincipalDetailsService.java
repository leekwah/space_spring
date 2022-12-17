package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // IoC 등록
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // 1. 로그인 요청인지 아닌지, 판단 -> username 만 확인하면 된다. 비밀번호는 스프링이 알아서 해준다.
    // 2. return 이 잘 되면, 자동으로 UserDetails 타입을 session 을 만들어 준다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            return null;
        } else {
            // return userEntity; // return 타입이 UserDetails 이 아니다. (그렇기에 새로운 클래스를 생성한다.)
            // new PrinicipalDetails() 가 session 에 저장된다고 생각하면 된다.
            return new PrincipalDetails(userEntity);
        }

    }
}
