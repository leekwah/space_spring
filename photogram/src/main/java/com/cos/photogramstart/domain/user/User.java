package com.cos.photogramstart.domain.user;

// JPA 를 사용하고 있다. - Java Persistence API (자바로 데이터를 영구적으로(DB) 저장할 수 있는 API 를 제공)

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    // 양방향 매핑
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // 한 명의 유저는 여러가지 이미지를 가질 수 있기에 @OneToMany
    @JsonIgnoreProperties({"user"}) // 내부에 있는 user 는 무시하고 파싱을 한다. (JPA 무한 참조 방지)
    private List<Image> images; // 컬렉션이 들어가는 타입이 필요한데, 없음
    // mappedBy = "user" 는 내가 연관관계 주인이 아니라는 뜻, 그러므로 테이블에 컬럼 생성 X, 그리고, "user" 는 이미지에 있는 변수와 동일해야한다.
    // User 를 SELECT 할 때, 해당 User id 로 등록된 image 를 다 가져오라는 뜻 - 대신 getImages() 함수가 호출될 때 가져오라는 것
    // fetch = FetchType.LAZY 일 때는, User 를 SELECT 할 때, 해당 User id 로 등록된 image 들을 가져오지않는 것
    // (Eager 인 경우에는, Join 해서 가져오라는 뜻)


    private LocalDateTime createDate;

    @PrePersist // DB 에 INSERT 되기 직전에 실행
    public void createDate() { this.createDate = LocalDateTime.now(); }


}
