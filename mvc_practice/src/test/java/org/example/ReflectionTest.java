package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/*
 * @Controller 애노테이션이 설정되어 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
    @Test
    void controllerScan() {
        Reflections reflections = new Reflections("org.example"); // 패키지 찾는 위치

        Set<Class<?>> beans = new HashSet<>();
        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class)); // reflections 해당 패키지에 있는 클래스 내에서 애노테이션이 붙어있는 클래스들을 찾는다는 것을 의미한다.
        beans.addAll(reflections.getTypesAnnotatedWith(Service.class)); // reflections 해당 패키지에 있는 클래스 내에서 애노테이션이 붙어있는 클래스들을 찾는다는 것을 의미한다.
        // 이후에 해쉬셋에 더한다.

        // beans에 담겨있다.
        logger.debug("beans: [{}]", beans);

    }
}
