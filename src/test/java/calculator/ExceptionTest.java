package calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExceptionTest {

    private final Calculator calculator = new Calculator();
    private final NumberExtractor numberExtractor = new NumberExtractor();

    @Test
    @DisplayName("커스텀 구분자 규칙을 만족하지 못한 경우 (문자열 앞부분, \"//\"와 \"\\n\" 사이)")
    void not_matching_custom_pattern() {
        Assertions.assertThatThrownBy(() -> {
            numberExtractor.extractFrom("/.\\n 1. 2 .3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("구분자가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("양수가 아닌 문자가 입력된 경우 ex) \"-1,15,4\", \"//:\\n0:1:3\", \"-1a2c\"")
    void not_matching_custom_pattern1() {
        Assertions.assertThatThrownBy(() -> {
            numberExtractor.extractFrom("-1,15,4");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("구분자가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("양수가 아닌 문자가 입력된 경우 ex) \"-1,15,4\", \"//:\\n0:1:3\", \"-1a2c\"")
    void not_matching_custom_pattern2() {
        Assertions.assertThatThrownBy(() -> {
            numberExtractor.extractFrom("//:\\n0:1:3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("구분자가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("양수가 아닌 문자가 입력된 경우 ex) \"-1,15,4\", \"//:\\n0:1:3\", \"-1a2c\"")
    void not_matching_custom_pattern2_1() {
        Assertions.assertThatThrownBy(() -> {
            numberExtractor.extractFrom("//:\\n3:01:3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("구분자가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("양수가 아닌 문자가 입력된 경우 ex) \"-1,15,4\", \"//:\\n0:1:3\", \"-1a2c\"")
    void not_matching_custom_pattern3() {
        Assertions.assertThatThrownBy(() -> {
            numberExtractor.extractFrom("1a2c");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("구분자가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("기본 구분자와 커스텀 구분자가 섞인 경우 ex) \"//:\\n1,2,3\"")
    void mixed_pattern_1() {
        Assertions.assertThatThrownBy(() -> {
            numberExtractor.extractFrom("//:\\n1,2,3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("구분자가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("기본 구분자와 커스텀 구분자가 섞인 경우 ex) \"//:\\n1,2,3\"")
    void mixed_pattern_2() {
        Assertions.assertThatThrownBy(() -> {
            numberExtractor.extractFrom("//,\\n1,2:3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("구분자가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("기본 구분자와 커스텀 구분자가 섞인 경우 ex) \"//:\\n1,2,3\"")
    void mixed_pattern_3() {
        Assertions.assertThatThrownBy(() -> {
            numberExtractor.extractFrom("//:\\n1:2,3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("구분자가 존재하지 않습니다.");
    }


    @Test
    @DisplayName("콤마, 쉼표가 아님에도 \"//\"와 \"\\n\" 사이에 문자가 위치하지 않은 경우")
    void no_custom_word() {
        Assertions.assertThatThrownBy(() -> {
            numberExtractor.extractFrom("//\\n1:2,3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("구분자가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("콤마, 쉼표가 아님에도 \"//\"와 \"\\n\" 사이에 문자가 위치했으나 문자열 앞부분이 아닌 경우")
    void custom_pattern_but_not_at_front() {
        Assertions.assertThatThrownBy(() -> {
            numberExtractor.extractFrom("1//:\\n:3:2:3");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("구분자가 존재하지 않습니다.");
    }
}
