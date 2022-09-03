package com.fastcampus.ch3;

import java.io.FileReader;
import java.util.Properties;

class Car {}
class SportCar extends Car {}
class Truck extends Car {}

public class Main1 {
    public static void main(String[] args) {
        Car car = new SportCar();
    }

    static Car getCar() {
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p, p.getProperty("car"));
    }
}
