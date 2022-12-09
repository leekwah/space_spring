package com.example.hello.dto;

public class Member {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public Member() {
        this.name = null;
        this.age = 0;
    }
    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "member{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
