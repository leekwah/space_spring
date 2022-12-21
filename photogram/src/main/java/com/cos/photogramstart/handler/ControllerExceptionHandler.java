package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class) // RuntimeException 이 발생하는 것들은 전부 이게 가로챈다
    public String validationException(CustomValidationException e) { // JavaScript 를 리턴
        // Script 와 CMRespDto 비교
        // 1. 클라이언트에게 응답할 때 - Script
        // 2. Ajax 통신 - CMRespDto (개발자가 JavaScript 로 응답받음)
        // 3. Android 통신 - CMRespDto (응답을 개발자가 받음)
        return Script.back(e.getErrorMap().toString());
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) { // Data 를 리턴
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }
}
