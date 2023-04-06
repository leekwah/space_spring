package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class CourseTest {
    @DisplayName("과목을 생성한다.")
    @Test
    void createTest() {
        assertThatCode(() -> new Course("OOP", 3, "A+"))
                .doesNotThrowAnyException(); // 해당 코스가 정상적으로 생성되면 어떠한 Exception도 발생하지 않는다.
    }
}
