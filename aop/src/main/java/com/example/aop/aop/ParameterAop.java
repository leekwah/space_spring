package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // aop 로 작성하기 위해선는 @Aspect를 사용
@Component
public class ParameterAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {}

    @Before("cut()") // cut() 메서드가 실행되기 전에
    private void before(JoinPoint joinPoint) { // 실행되기 전에 넘어가는 arguments가 무엇인지 보기 위해서 작성함
        // JoinPoint 는 객체가 들어가는 위치를 알 수 있는 객체를 줌

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs(); // 매개변수들의 배열들을 의미함

        for (Object obj : args) {
            System.out.println("type : " + obj.getClass().getSimpleName());
            System.out.println("value : " + obj);

        }

    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    private void afterReturn(JoinPoint joinPoint, Object returnObj) { // 실행된 후에 넘어가는 arguments가 무엇인지 보기 위해서 작성함
        System.out.println("returnObject");
        System.out.println(returnObj);

    }
}
