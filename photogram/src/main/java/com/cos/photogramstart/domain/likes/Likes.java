package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
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
// 두 개의 컬럼을 Unique 로 쓸려면 아래와 같이 @Table 을 사용한다.
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name="likes_uk",
                        columnNames = {"imageId", "userId"} // 중복되지 못하게 유니크 제약조건을 걸은 것
                )
        }
)
public class Likes { // Like 가 MariaDB 나 MySQL 에 있기 때문에
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 DB 를 따라간다.
    private int id; // 엄청 큰 대형회사일 경우에는 Long 을 사용

    @JoinColumn(name = "imageId")
    @ManyToOne // 하나의 이미지는 여러개의 좋아요가 있을 수 있음
    // @ManyToOne 은 Fetch.EAGER 전략이다.
    private Image image;

    // 위에 Image 를 가져오기 때문에, 추후에 안되게 함
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    private LocalDateTime createDate;

    /*
        @PrePersist // native query 를 사용할 경우 들어가지 않는다.
        public void createDate() {
            this.createDate = LocalDateTime.now();
        }
     */
}
