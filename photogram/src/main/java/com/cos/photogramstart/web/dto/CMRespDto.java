package com.cos.photogramstart.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CMRespDto<T> { // 전역적으로 쓸 것이기 때문에, 제네릭인 T 를 사용한다.
    private int code; // 1은 (성공), -1 (실패)
    private String message;
    private T data; // T 타입이 위에 것과 똑같이 지정된다.
}
