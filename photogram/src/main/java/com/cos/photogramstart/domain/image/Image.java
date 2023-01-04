package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
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
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 DB 를 따라간다.
    private int id; // 엄청 큰 대형회사일 경우에는 Long 을 사용
    private String caption; // 글 내용들을 넣음
    private String postImageUrl; // 사진을 전송받아서 그 사진을 서버 특정 폴더에 저장 -> DB 에는 저장된 경로를 insert -> 즉, 경로를 저장하는 것이다.

    @ManyToOne // 1명의 User 는 N 개의 이미지 O, 1개의 이미지는 N 의 유저는 X => N : 1 의 관계
    @JoinColumn(name = "userId") // user 가 DB 에 저장하게 될 때, 문제가 됨 -> FK 키로 저장됨
    @JsonIgnoreProperties({"images"}) // images 는 무시해라는 뜻
    private User user; // 누가 업로드 한지 알아야 하기 때문에


    // 이미지 좋아요
    // 정보 필요함 -> 양방향 매핑
    @JsonIgnoreProperties({"image"}) // 무한 참조 방지
    @OneToMany(mappedBy = "image") // likes 의 image 변수 이름
    private List<Likes> likes; // Image 를 선택할 때 Likes 도 같이 가져오게 된다.

    @Transient // DB 에 컬럼이 만들어지지 않는다.
    private boolean likeState;

    // 좋아요 개수
    @Transient
    private int likeCount;

    // 이미지 댓글 정보 필요함


    private LocalDateTime createDate;

    @PrePersist
    private void createDate() {
        this.createDate = LocalDateTime.now();
    }

    /*
    오브젝트를 콘솔에 출력할 때 문제가될 수 있어서 User 부분을 없앴음 -> 무한참조 방지
    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", postImageUrl='" + postImageUrl + '\'' +
                ", createDate=" + createDate +
                '}';
    }
     */
}
