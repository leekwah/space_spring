package com.cos.photogramstart.domain.user;

// JPA 를 사용하고 있다. - Java Persistence API (자바로 데이터를 영구적으로(DB) 저장할 수 있는 API 를 제공)

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // DB 에 테이블을 생성
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 DB 를 따라간다.
    private int id; // 엄청 큰 대형회사일 경우에는 Long 을 사용

    @Column(length = 20, unique = true) // 제약조건이 걸린 것이다. 동일한 값이 저장하지 못한다. UniqueKey 설정
    private String username;
    @Column(nullable = false) // null 불가능
    private String password;

    @Column(nullable = false)
    private String name;
    private String website;
    private String bio;
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // 추후에 작성자의 프로필 사진
    private String role; // 권한

    private LocalDateTime createDate;

    @PrePersist // DB 에 INSERT 되기 직전에 실행
    public void createDate() { this.createDate = LocalDateTime.now(); }


}
