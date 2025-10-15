package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExtractorTest {

    @Test
    @DisplayName("공백이면 0 추출")
    void when_inputIsBlank_0() {
        NumberExtractor numberExtractor = new NumberExtractor();
        List<Integer> extractedNumbers = numberExtractor.extractFrom("");
        assertThat(extractedNumbers).containsExactly(0);
    }


}
