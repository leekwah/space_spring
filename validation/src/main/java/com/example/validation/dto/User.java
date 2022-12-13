package com.example.validation.dto;

import com.example.validation.annotation.YearMonth;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class User {
    @NotBlank
    private String name;
    @Min(value = 90) // 값을 90으로 설정
    private int age;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. XXX-XXXX-XXXX")
    private String phoneNumber;

    @YearMonth
    private String reqYearMonth; // yyyyMM

    @Valid // 다른 DTO 에 Validation 을 했다고 해도, @Valid 를 적어야 정상적으로 검사를 진행한다.
    private List<Car> cars;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    /* CustomValidation 를 하지 않았을 경우에는 AssertTrue 와 같은 것을 사용한다.
        @AssertTrue(message = "yyyyMM 의 형식에 맞지 않습니다.")
        public boolean isReqYearMonthValidation() {
            try {
                LocalDate localDate = LocalDate.parse(getReqYearMonth()+"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        */

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
                ", cars=" + cars +
                '}';
    }
}
