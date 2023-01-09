package com.cos.photogramstart.handler.aop;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Aspect // AOP 처리하는 핸들러가 됨
@Component // RestController, Service 등 Component 구현체이기 때문에 Component 를 상속해서 만들어져 있음
public class ValidationAdvice { // Advice 를 공통기능이라고 함

    // @Before 는 어떤 함수가 실행되기 전
    // @After 는 함수가 끝난 뒤에 실행
    // @Around 는 실행과 끝 전부 다
    @Around("execution(* com.cos.photogramstart.web.*Controller.*(..))") // *(..) 은 메서드의 파라미터가 뭐든 상관 없는 것
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable { // api 쪽 advice

        // proceedingJoinPoint 는 모든 곳(내,외부)에 접근 가능한 파라미터 (메서드 매개변수건 어디건 가능)
        // 특정함수가 실행될 때, 함수의 모든 정보를 담고, 함수보다 먼저 실행됨
        // proceedJointPoint 는 함수 매개변수에 들어가는 값을 알 수 있음
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {

                BindingResult bindingResult = (BindingResult) arg; // 다운캐스팅

                if (bindingResult.hasErrors()) { // bindingResult 에 Error 가 있을 경우
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
                }
            }
        }

        return proceedingJoinPoint.proceed(); // 그 실행한 함수로 다시 돌아가라는 것
    }

    @Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))") // *(..) 은 메서드의 파라미터가 뭐든 상관 없는 것
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable { // 그냥 advice

        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg; // 다운캐스팅
                if (bindingResult.hasErrors()) { // bindingResult 에 Error 가 있을 경우
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    // throw new RuntimeException("유효성 검사 실패함");
                    throw new CustomValidationException("유효성 검사 실패함", errorMap);
                }
            }
        }

        return proceedingJoinPoint.proceed();
    }

}
