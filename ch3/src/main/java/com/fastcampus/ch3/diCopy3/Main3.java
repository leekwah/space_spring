package com.fastcampus.ch3.diCopy3;

import com.google.common.reflect.ClassPath;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component class Car {}
@Component class SportsCar extends Car {}
@Component class Truck extends Car {}
@Component class Engine {}

class AppContext {
    Map map; // 객체 저장소

    AppContext() {
        map = new HashMap();
        doComponentScan();
    }

    private void doComponentScan() {
        // guava 라이브러리를 사용한다.
        // 1. 패키지 내의 클래스 목록을 가져온다.
        // 2. 반복문으로 클래스를 하나씩 읽어와서 @Component 이 붙어있는지 확인
        // 3. @Componenet 가 붙어있으면, 객체를 생성해서 map 에 저장
        try {
            ClassLoader classLoader = AppContext.class.getClassLoader();
            ClassPath classPath = ClassPath.from(classLoader);

            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");

            for (ClassPath.ClassInfo classInfo : set) {
                Class clazz = classInfo.load();
                Component component = (Component) clazz.getAnnotation(Component.class);
                if(component != null){
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());
                    map.put(id, clazz.getComponentType());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key) { return map.get(key); }
    Object getBean(Class clazz) {
        for(Object obj : map.values()) {
            if (clazz.isInstance(obj))
                return obj;

        }
        return null;
    }
}

public class Main3 {
    public static void main(String[] args)  throws Exception {
        AppContext ac = new AppContext();
        Car car = (Car)ac.getBean("car"); // byName으로 검색
        Car car2 = (Car)ac.getBean(Car.class); // byType으로 객체를 검색
        System.out.println("car = " + car); // car = com.fastcampus.ch3.diCopy2.Truck@47f6473 출력
        Engine engine = (Engine)ac.getBean("engine");
        System.out.println("engine = " + engine); // engine = com.fastcampus.ch3.diCopy2.Engine@15975490 출력
    }
}