package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 Security 를 활성화 시킴
@Configuration // IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http); <- 이것이 실행되는 중이라서, redirct 가 된다.
        // super 를 삭제하면, 기존 Security 가 가지고 있는 기능이 다 비활성화가 된다.

        // 인증이 되지 않은 사용자는 전부 login 으로 가게 한다.
        // anMatchers 에 적힌 주소들은 전부 403 을 뜨게 한다. (권한 없음)
        // 이후에 로그인이 되지 않은 (권한이 없는) 경우에는 /auth/signin 으로 가게 한다.
        // 로그인이 된 경우에는 / 으로 가게 한다.

        http.csrf().disable(); // scrf 기능을 비활성화 한다. (추후 JavaScript 등과 같은 걸 할 때 힘듦)

        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**")
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin")
                .defaultSuccessUrl("/");
    }
}
