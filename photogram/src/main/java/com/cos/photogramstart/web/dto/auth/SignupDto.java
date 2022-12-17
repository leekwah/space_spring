package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data // Getter 와 Setter 를 만들어주는 어노테이션
public class SignupDto {
    @Size(min = 2, max = 20) // 최소 2, 최대 20
    @NotBlank
    private String username;
    @NotBlank // null, 빈칸 불가능
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }
}
