package org.example;

import java.util.List;

public class GradeCalculator {
    private final Courses courses;


    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    public double calculateGrade() {

        // (학점 수 * 교과목 평점)의 합계
        // 일급 컬렉션으로 할 경우에는, 컬렉션에게 위임할 수 있다.
        double totalMultipliedCreditAndCourseGrade = courses.multiplyCreditAndCourseGrade();

        // 수강신청 총 학점 수
        int totalCompletedCredit = courses.calculateTotalCompletedCredit();

        // (학점 수 * 교과목 평점)의 합계 / 수강신청 총 학점 수
        return totalMultipliedCreditAndCourseGrade / totalCompletedCredit;
    }
}
