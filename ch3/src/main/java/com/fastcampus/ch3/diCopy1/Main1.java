package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

class Car {}
class SportsCar extends Car {}
class Truck extends Car {}
class Engine {}

public class Main1 {
    public static void main(String[] args)  throws Exception {
        // car 를 반환하는 getCar() 메서드를 생성
        // Car car = getCar();
        // System.out.println("car = " + car); // car = com.fastcampus.ch3.diCopy1.SportsCar@2ef9b8bc 출력
        // Truck 으로 바꾸고 싶으면, config.txt 를 car=com.fastcampus.ch3.diCopy1.Truck 로 변경하면 된다.
        // 그렇게하면,  car = com.fastcampus.ch3.diCopy1.Truck@2ef9b8bc 출력

        // getObject() 메서드를 통해서 출력
        Car car = (Car)getObject("car");
        System.out.println("car = " + car); // car = com.fastcampus.ch3.diCopy1.Truck@2ef9b8bc 출력
        Engine engine = (Engine)getObject("engine");
        System.out.println("engine = " + engine); // engine = com.fastcampus.ch3.diCopy1.Engine@1e67b872 출력
    }

    // 현재는 간단하게
    static Car getCar() throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty("car"));

        return ((Car)clazz.newInstance());
    }

    static Object getObject(String key) throws Exception {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty(key));

        return clazz.newInstance();
    }
}
