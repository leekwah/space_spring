package com.example.hello.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PutPostRequestDTO {
    private String name;
    private int age;

    private List<CarDTO> carlist;

    @Override
    public String toString() {
        return "PutPostRequestDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", carlist=" + carlist +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<CarDTO> getCarlist() {
        return carlist;
    }

    public void setCarlist(List<CarDTO> carlist) {
        this.carlist = carlist;
    }
}
