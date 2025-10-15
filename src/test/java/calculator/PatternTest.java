package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PatternTest {

    @Nested
    @DisplayName("공백은 Pattern 검증 전 제거되었다고 가정")
    class BasicPattern {
        @Test
        @DisplayName("기본 구분자(콤마)와 양수로 구성되어 있으면 성공")
        void when_include_basic_delimeter_COMMA() {
            BasicPatternValidator basicPatternValidator = new BasicPatternValidator();
            boolean isValidatedPattern = basicPatternValidator.validate("1,2,3");
            assertThat(isValidatedPattern).isEqualTo(true);
        }

        @Test
        @DisplayName("기본 구분자(콜론)와 양수로 구성되어 있으면 성공")
        void when_include_basic_delimeter_COLON() {
            BasicPatternValidator basicPatternValidator = new BasicPatternValidator();
            boolean isValidatedPattern = basicPatternValidator.validate("1:2:3");
            assertThat(isValidatedPattern).isEqualTo(true);
        }

        @Test
        @DisplayName("기본 구분자여도 양수 만족하지 못하면 실패")
        void when_include_0_in_front_of_positive_number() {
            BasicPatternValidator basicPatternValidator = new BasicPatternValidator();
            boolean isValidatedPattern = basicPatternValidator.validate("1,02,3");
            assertThat(isValidatedPattern).isEqualTo(false);
        }

        @Test
        @DisplayName("기본 구분자여도 음수가 존재하면 실패")
        void when_include_negative_number() {
            BasicPatternValidator basicPatternValidator = new BasicPatternValidator();
            boolean isValidatedPattern = basicPatternValidator.validate("-11,2,3");
            assertThat(isValidatedPattern).isEqualTo(false);
        }

        @Test
        @DisplayName("기본 구분자가 섞여있으면 실패")
        void when_mixed_comma_colon() {
            BasicPatternValidator basicPatternValidator = new BasicPatternValidator();
            boolean isValidatedPattern = basicPatternValidator.validate("1,2:3");
            assertThat(isValidatedPattern).isEqualTo(false);
        }
    }
}
