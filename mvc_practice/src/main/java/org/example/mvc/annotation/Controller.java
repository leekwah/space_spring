package org.example.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // 이렇게 해야지, 클래스에 붙일 수 있는 어노테이션이 된다.
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}