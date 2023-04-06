package org.example;

import java.util.List;

public class GradeCalculator {
    private final List<Course> courses;

    public GradeCalculator(List<Course> courses) {
        this.courses = courses;
    }

    public double calculateGrade() {
        double multipliedCreditAndCourseGrade = 0;

        // (학점 수 * 교과목 평점)의 합계
        for (Course course : courses) {
            multipliedCreditAndCourseGrade += course.multiplyCreditAndCourseGrade();
            // multipliedCreditAndCourseGrade += course.getCredit() * course.getGradeToNumber(); // 학점과 성적을 숫자로 변경한 값을 곱한다.
        }

        // 수강신청 총 학점 수
        int totalCompletedCredit = courses.stream()
                .mapToInt(Course::getCredit)
                .sum();

        // (학점 수 * 교과목 평점)의 합계 / 수강신청 총 학점 수
        return multipliedCreditAndCourseGrade / totalCompletedCredit;
    }
}
