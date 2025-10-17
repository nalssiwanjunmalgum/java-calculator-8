package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExtractorTest {

    @Test
    @DisplayName("빈 문자열을 입력한 경우 0을 반환합니다")
    void when_inputIsBlank_0() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Long> extractedNumbers = numberExtractor.extractFrom("");
        assertThat(extractedNumbers).containsExactly(0L);
    }

    @Test
    @DisplayName("구분자 없이 양수만 입력된 경우")
    void when_onlyNumbers_excludeBlank() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Long> extractedNumbers = numberExtractor.extractFrom("1 2 3 ");
        assertThat(extractedNumbers).containsExactly(123L);
    }

    @Test
    @DisplayName("기본 구분자, 숫자 하나")
    void when_basic_pattern_single_number() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Long> extractedNumbers = numberExtractor.extractFrom("1");
        assertThat(extractedNumbers).containsExactly(1L);
    }

    @Test
    @DisplayName("기본 구분자인 경우")
    void when_basic_pattern() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Long> extractedNumbers = numberExtractor.extractFrom("1,2:3 ");
        assertThat(extractedNumbers).containsExactly(1L, 2L, 3L);
    }

    @Test
    @DisplayName("커스텀 구분자가 정상적으로 출력되는지 확인")
    void when_custom_pattern_Custom_Delimiter() {
        NumberExtractor numberExtractor = new NumberExtractor();
        String foundDelimiter = numberExtractor.findDelimiter("//!\\n3!13!5");
        assertThat(foundDelimiter).isEqualTo("!");
    }

    @Test
    @DisplayName("커스텀 구분자로 정상적으로 구분하는지 확인")
    void when_custom_pattern_extractedNumbers() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Long> extractedNumbers = numberExtractor.extractFrom("//!\\n      3!  1  3 ! 5");
        assertThat(extractedNumbers).containsExactly(3L, 13L, 5L);
    }

    @Test
    @DisplayName("커스텀 구분자, 숫자 하나")
    void when_custom_pattern_single_number() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Long> extractedNumbers = numberExtractor.extractFrom("//!\\n      3");
        assertThat(extractedNumbers).containsExactly(3L);
    }

    @Test
    @DisplayName("커스텀 구분자, 숫자 하나")
    void when_custom_pattern_single_number2() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Long> extractedNumbers = numberExtractor.extractFrom("//;\\n1");
        assertThat(extractedNumbers).containsExactly(1L);
    }

    @Test
    @DisplayName("커스텀 구분자로 정상적으로 구분하는지 확인")
    void when_custom_pattern_extractedNumbers_2() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Long> extractedNumbers = numberExtractor.extractFrom("//?\\n      3?  1?3? 5");
        assertThat(extractedNumbers).containsExactly(3L, 1L, 3L, 5L);
    }

    @Test
    @DisplayName("콤마, 콜론이 혼용된 경우 정상적으로 구분되는지 확인")
    void when_basic_pattern_extractedNumbers() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Long> extractedNumbers = numberExtractor.extractFrom(" 3,  1:3 5");
        assertThat(extractedNumbers).containsExactly(3L, 1L, 35L);
    }
}
