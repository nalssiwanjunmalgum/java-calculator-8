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


}
