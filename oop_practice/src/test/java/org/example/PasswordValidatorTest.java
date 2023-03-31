package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

/*
 * 요구사항
 * 비밀번호는 최소 8자 이상 12자 이하여야 한다.
 * 비밀번호가 8자 미만 또는 12자 초과인 경우 IllegalArgumentException 예외를 발생시킨다.
 * 경계조건에 대해 테스트 코드를 작성해야한다.
 */
public class PasswordValidatorTest {
    @DisplayName("비밀번호가 최소 8자 이상, 12자 이하면 예외가 발생하지 않는다.") // 어떤 테스트인지 의도를 명시해주면 좋다.
    @Test
    void validatePasswordTest() {
        assertThatCode(() -> PasswordValidator.validate("leekwahoon"))
                .doesNotThrowAnyException(); // assertThatCode 안에 있는 부분이 호출되었을 때, 예외가 발생되지 않음을 확인할 때 사용하는 메서드 (테스트 통과)
    }

    @DisplayName("비밀번호가 8자 미만 12자 초과인 경우에는 IllegalArgumentException 예외가 발생한다.")
    @Test
    void validatePasswordTest2() {
        assertThatCode(() -> PasswordValidator.validate("aabb"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 최소 8자 이상 12자 미만이여야 한다.");
    }
}
