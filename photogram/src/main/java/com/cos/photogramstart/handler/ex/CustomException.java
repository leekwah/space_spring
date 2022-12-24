package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomException extends RuntimeException {
    // 객체를 구분하기 위해서 사용한다.
    private static long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }
}
