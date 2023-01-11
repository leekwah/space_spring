package com.cos.photogramstart.config.oauth;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {
    // 서비스를 만든 이유
    // facebook 로그인 버튼을 클릭시, 페이스북 로그인 창이 뜨게 됨.
    // 페이스북에게 정보를 날림, 이후 인증되면 응답을 해줌
    // 이 응답을 처리하는 곳이 OAuth2DetailsService 이다.

    private final UserRepository userRepository; // User Object 에 save 하기 위해서 UserRepository 가 필요하다.

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // userRequest 안에 정보들을 전달해준다.

        OAuth2User oauth2User = super.loadUser(userRequest);

        Map<String, Object> userInfo = oauth2User.getAttributes();
        String name = (String) userInfo.get("name"); // K, V 기 때문에, Key 를 적어주고, Object 타입이기에, 다운캐스팅을 한다.
        String email = (String) userInfo.get("email"); // K, V 기 때문에, Key 를 적어주고, Object 타입이기에, 다운캐스팅을 한다.
        String username = "facebook_" + (String) userInfo.get("id"); // 충돌안나게 하기 위해서
        String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString()); // 패스워드는 암호화가 되어서 들어가야함 -> bCrypt 필요 // 몰라도 되는 패스워드지만, 암호화는 필요한 것
        // 메모리에 IoC로 등록 때, 사이클 때문에 순서가 잘못되면, 강제로 new 로 만든다.
        // (원래는 위에 private final BCryptPasswordEncoder 를 사용했음)

        // 한 번 OAuth2 로그인으로 저장한 뒤에, 페이스북으로 로그인하면 save 를 하면 안됨 -> 회원정보가 있는지 없는지 살펴봐야함
        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) { // facebook 최초 로그인
            // user 정보 설정하기
            User user = User.builder() // 필수값은 Dto 로 보는 것이 좋음
                    .username(username)
                    .password(password)
                    .email(email)
                    .name(name)
                    .role("ROLE_USER") // 권한 필요
                    .build();

            // return userRepository.save(user); // 유저 정보를 세션에 저장한다.
            // 하지만 위의 방법의 경우에는 값이 맞지 않게 되기 때문에, 아래처럼 PrincipalDetails 를 사용한다.
            return new PrincipalDetails(userRepository.save(user), oauth2User.getAttributes()); // 유저 정보를 세션에 저장한다.
        } else { // 이미 facebook 으로 회원가입 되어 있음
            return new PrincipalDetails(userEntity, oauth2User.getAttributes()); // 그 값을 세션으로 갖고 온다.
        }
    }
}
