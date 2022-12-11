package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component // @Bean과 @Component 의 차이 = @Bean 은 클래스에 붙일 수 없고, 메서드에 붙일 수 있다.
public class TimerAop { // 특정 메소드의 실행시간을 찍을 것이다.
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {}

    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer() {}

    @Around("cut() && enableTimer()") // 두가지 조건을 같이 쓴다는 의미이다.
    private void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
    }
}
