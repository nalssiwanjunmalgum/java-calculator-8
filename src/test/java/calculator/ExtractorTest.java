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
        List<Integer> extractedNumbers = numberExtractor.extractFrom("");
        assertThat(extractedNumbers).containsExactly(0);
    }

    @Test
    @DisplayName("구분자 없이 양수만 입력된 경우")
    void when_onlyNumbers_excludeBlank() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Integer> extractedNumbers = numberExtractor.extractFrom("1 2 3 ");
        assertThat(extractedNumbers).containsExactly(123);
    }

    @Test
    @DisplayName("커스텀 구분자가 정상적으로 출력되는지 확인")
    void when_custom_pattern_Custom_Delimiter() {
        NumberExtractor numberExtractor = new NumberExtractor();
        String foundDelimiter = numberExtractor.findDelimiter("//!\n3!13!5");
        assertThat(foundDelimiter).isEqualTo("!");
    }

    @Test
    @DisplayName("커스텀 구분자로 정상적으로 구분하는지 확인")
    void when_custom_pattern_extractedNumbers() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Integer> extractedNumbers = numberExtractor.extractFrom("//!\n      3!  1  3 ! 5");
        assertThat(extractedNumbers).containsExactly(3, 13, 5);
    }

    @Test
    @DisplayName("커스텀 구분자로 정상적으로 구분하는지 확인")
    void when_custom_pattern_extractedNumbers_2() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Integer> extractedNumbers = numberExtractor.extractFrom("//?\n      3?  1?3? 5");
        assertThat(extractedNumbers).containsExactly(3, 1, 3, 5);
    }


}
