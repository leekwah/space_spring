package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException {
    // 객체를 구분하기 위해서 사용한다.
    private static long serialVersionUID = 1L;
    // ErrorMap 이 여러개 있을 리가 없기 때문에, 이것만 한다. (다른 Exception 들은 ErrorMap 이 있음)
    public CustomApiException(String message) {
        super(message);
    }
}
