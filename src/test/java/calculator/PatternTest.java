package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import calculator.validator.old.BasicPatternValidator;
import calculator.validator.old.CustomPatternValidator;
import calculator.validator.old.NumberPatternValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PatternTest {

    @Nested
    @DisplayName("공백은 Pattern 검증 전 제거되었다고 가정")
    class BasicPatternTest {
        @Test
        @DisplayName("기본 구분자(콤마)와 양수로 구성되어 있으면 성공")
        void when_include_basic_delimeter_COMMA() {
            BasicPatternValidator basicPatternValidator = new BasicPatternValidator();
            boolean isValidatedPattern = basicPatternValidator.validate("1*2*3");
            assertThat(isValidatedPattern).isEqualTo(true);
        }

        @Test
        @DisplayName("기본 구분자(콜론)와 양수로 구성되어 있으면 성공")
        void when_include_basic_delimiter_COLON() {
            BasicPatternValidator basicPatternValidator = new BasicPatternValidator();
            boolean isValidatedPattern = basicPatternValidator.validate("1*2*3");
            assertThat(isValidatedPattern).isEqualTo(true);
        }

        @Test
        @DisplayName("기본 구분자여도 양수 만족하지 못하면 실패")
        void when_include_0_in_front_of_positive_number() {
            BasicPatternValidator basicPatternValidator = new BasicPatternValidator();
            boolean isValidatedPattern = basicPatternValidator.validate("1*02*3");
            assertThat(isValidatedPattern).isEqualTo(false);
        }

        @Test
        @DisplayName("기본 구분자여도 음수가 존재하면 실패")
        void when_include_negative_number() {
            BasicPatternValidator basicPatternValidator = new BasicPatternValidator();
            boolean isValidatedPattern = basicPatternValidator.validate("-11*2*3");
            assertThat(isValidatedPattern).isEqualTo(false);
        }
    }

    @Nested
    class CustomPatternTest {
        @Test
        @DisplayName("커스텀 구분자의 규칙에 만족하면 성공")
        void when_custom_pattern_0() {
            CustomPatternValidator customPatternValidator = new CustomPatternValidator();
            boolean isValidatedPattern = customPatternValidator.validate("//;\\n1");
            assertThat(isValidatedPattern).isEqualTo(true);
        }

        @Test
        @DisplayName("커스텀 구분자의 규칙에 만족하면 성공 (;)")
        void when_custom_pattern_1() {
            CustomPatternValidator customPatternValidator = new CustomPatternValidator();
            boolean isValidatedPattern = customPatternValidator.validate("//;\\n1;2;3");
            assertThat(isValidatedPattern).isEqualTo(true);
        }

        @Test
        @DisplayName("커스텀 구분자의 규칙에 만족하면 성공 (;)")
        void when_custom_pattern_2() {
            CustomPatternValidator customPatternValidator = new CustomPatternValidator();
            boolean isValidatedPattern = customPatternValidator.validate("//?\\n1?2?3");
            assertThat(isValidatedPattern).isEqualTo(true);
        }

        @Test
        @DisplayName("커스텀 구분자의 규칙에 만족하면 성공 (:)")
        void when_custom_pattern_3() {
            CustomPatternValidator customPatternValidator = new CustomPatternValidator();
            boolean isValidatedPattern = customPatternValidator.validate("//:\\n1:2:3");
            assertThat(isValidatedPattern).isEqualTo(true);
        }

        @Test
        @DisplayName("커스텀 구분자의 규칙에 만족하면 성공 (,)")
        void when_custom_pattern_4() {
            CustomPatternValidator customPatternValidator = new CustomPatternValidator();
            boolean isValidatedPattern = customPatternValidator.validate("//,\\n1,2,3");
            assertThat(isValidatedPattern).isEqualTo(true);
        }
    }

    @Nested
    class NumberPatternTest {
        @Test
        @DisplayName("일반적인 양수라면 성공")
        void when_number_pattern() {
            NumberPatternValidator numberPatternValidator = new NumberPatternValidator();
            boolean isValidatedPattern = numberPatternValidator.validate("123");
            assertThat(isValidatedPattern).isEqualTo(true);
        }
    }
}
