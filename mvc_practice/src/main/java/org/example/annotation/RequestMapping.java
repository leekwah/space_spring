package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) // 클래스에도 붙일 수 있지만, 메서드에도 붙일 수 있다.
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default ""; // 아무값도 입력하지 않을 시에, 빈 문자열로 입력하도록 한다.

    RequestMethod[] method() default {}; // get, post 메서드들이 여러개가 될 수 있기 때문에 배열 형식으로 지정한다.
}
