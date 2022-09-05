package com.fastcampus.ch3.diCopy2;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car {}
class SportsCar extends Car {}
class Truck extends Car {}
class Engine {}

class AppContext {
    Map map; // 객체 저장소

    AppContext() {
        // map.put("car", new SportsCar());
        // map.put("engine", new Engine());
        try {
            Properties p = new Properties();
            p.load(new FileReader("config.txt"));

            map = new HashMap(p); // Properties 에 저장된 내용을 map 에 저장

            // 반복문으로 클래스 이름을 얻어서 객체를 생성한 뒤 다시 map 에 저장
            for (Object key : map.keySet()){
                Class clazz = Class.forName((String)map.get(key));
                map.put(key, clazz.newInstance());
            }
            // com.fastcampus.ch3.diCopy2.Truck@1e67b872 출력
            // engine = com.fastcampus.ch3.diCopy2.Engine@60addb54 출력

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Object getBean(String key) {
        return map.get(key);
    }
}

public class Main2 {
    public static void main(String[] args)  throws Exception {
        AppContext ac = new AppContext();
        Car car = (Car)ac.getBean("car");
        System.out.println("car = " + car); //car = com.fastcampus.ch3.diCopy2.SportsCar@246ae04d 출력
        Engine engine = (Engine)ac.getBean("engine");
        System.out.println("engine = " + engine); // engine = com.fastcampus.ch3.diCopy2.Engine@62043840 출력
    }
}