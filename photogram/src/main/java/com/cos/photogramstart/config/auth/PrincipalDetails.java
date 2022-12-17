package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    private static final long sericalVersionUID = 1L;

    // 결국 여기에 User Object 를 담는 것이다.
    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    @Override // 권한을 가져오는 함수 - 컬렉션 (권한은 하나가 아닐 수 있기 때문에)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> { return user.getRole(); }); // 람다식으로
        return collector;
    }

    @Override // 패스워드
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override // 계정이 만기됐는지
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정이 잠겼는지
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 패스워드 만료 여부
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 활성화 여부
    public boolean isEnabled() {
        return true;
    }
}
