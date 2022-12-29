package com.cos.photogramstart.web.dto.subscirbe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscribeDto {
    private int id; // 로그인을 했을 경우에, 누군가에 대한 정보 (그 id 값)
    private String username;
    private String profileImageUrl; // 사진 때문에 필요
    private Integer subscribeState;
    private Integer equalUserState;

}
