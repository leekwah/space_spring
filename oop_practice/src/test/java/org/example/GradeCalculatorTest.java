package org.example;

/*
 * 요구사항
 * 평균학점 계산 방법 = (학점수 * 교과목 평점)의 합계/수강신청 총 학점 수
 * 일급 컬렉션 사용
 */

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class GradeCalculatorTest {
    // 1. 학점 계산기(도메인)를 구성하는 객체에는 어떤 것들이 있는지 고민 -> 이수한 과목, 학점 계산기
    // 2. 객체들 간의 관계를 고민
    // 3. 동적인 객체를 정적인 타입으로 추상화해서 도메인 모델링 하기
    // 4. 협력을 설계
    // 5. 객체들을 포괄하는 타입에 적절한 책임을 할당
    // 6. 구현하기

    @Test
    void calculateGradeTest() {
        List<Course> courses = List.of(new Course("OOP", 3, "A+"),
                new Course("자료구조", 3, "A+"));

        GradeCalculator gradeCalculator = new GradeCalculator(courses); // 이수한 과목을 전달받은 다음에
        // 성적을 계산해달라는 요청을 하면 학점 계산기는 성적을 전달해줄 것이다.
        double gradeResult = gradeCalculator.calculateGrade();

        assertThat(gradeResult).isEqualTo(4.5);

    }

}
