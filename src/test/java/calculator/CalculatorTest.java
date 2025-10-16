package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    @DisplayName("int형 수를 복수 개 입력 받으면 합을 계산합니다")
    void when_get_numbers_return_sum() {
        List<Long> numbers = new ArrayList<>(List.of(1L, 2L, 3L));
        Calculator calculator = new Calculator();
        long sum = calculator.execute(numbers);
        assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("Long 형 단독 수를 입력 받은 경우")
    void when_get_single_number_return_sum() {
        List<Long> numbers = new ArrayList<>(List.of(2200000000L));
        Calculator calculator = new Calculator();
        long sum = calculator.execute(numbers);
        assertThat(sum).isEqualTo(2200000000L);
    }

    @Nested
    @DisplayName("Extractor와 함께 묶어서 테스트를 진행합니다")
    class withExtractorTest {

        @Test
        @DisplayName("3 + 1 + 35")
        void testcase_1() {
            NumberExtractor numberExtractor = new NumberExtractor();
            List<Long> extractedNumbers = numberExtractor.extractFrom(" 3,  1:3 5");
            Calculator calculator = new Calculator();
            long sum = calculator.execute(extractedNumbers);
            assertThat(sum).isEqualTo(39L);
        }

    }


}
