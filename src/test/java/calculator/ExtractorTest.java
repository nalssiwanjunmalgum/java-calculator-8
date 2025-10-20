package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
    @DisplayName("커스텀 구분자가 정상적으로 출력되는지 확인")
    void when_custom_pattern_Custom_Delimiter2() {
        NumberExtractor numberExtractor = new NumberExtractor();
        String foundDelimiter = numberExtractor.findDelimiter("//^\\n3^13^5");
        assertThat(foundDelimiter).isEqualTo("^");
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

    @Nested
    @DisplayName("static method를 활용한 리팩토링")
    class RefactorV1 {

        @Test
        void number_regexValidator() {
            String onlyNumber = "123";
            boolean isNumber = RegexValidators.isNumber(onlyNumber);

            CommonParser commonParser = new CommonParser(" ");
            List<Long> numbers = commonParser.extractNumber(onlyNumber);

            assertThat(isNumber).isEqualTo(true);
            assertThat(numbers).containsExactly(123L);
        }

        @Test
        @DisplayName("COMMA 그리고 COLON 혼용되어도 상관없음")
        void basic_regexValidator() {
            String basicInput = "1,2:3";
            boolean isBasic = RegexValidators.isBasic(basicInput);

            String replacedInput = basicInput.replaceAll("[,:]", "*");
            CommonParser commonParser = new CommonParser("*");
            List<Long> numbers = commonParser.extractNumber(replacedInput);

            assertThat(isBasic).isEqualTo(true);
            assertThat(numbers).containsExactly(1L, 2L, 3L);
        }

        @Test
        void custom_regexValidator() {
            boolean custom = RegexValidators.isCustom("//?\\n3?1?3?5");
            assertThat(custom).isEqualTo(true);
            Result parse = CustomParser.parse("//?\\n3?1?3?5");

            List<Long> numbers = parse.extractNumber();
            assertThat(numbers).containsExactly(3L, 1L, 3L, 5L);
        }

        @Test
        void custom_regexValidator2() {
            boolean custom = RegexValidators.isCustom("//;\\n1");
            assertThat(custom).isEqualTo(true);
            Result parse = CustomParser.parse("//;\\n1");

            List<Long> numbers = parse.extractNumber();
            assertThat(numbers).containsExactly(1L);
        }

        @Test
        void custom_regexValidator3() {
            boolean custom = RegexValidators.isCustom("//:\\n1:2:5");
            assertThat(custom).isEqualTo(true);

            Result parse = CustomParser.parse("//:\\n1:2:5");

            List<Long> numbers = parse.extractNumber();
            assertThat(numbers).containsExactly(1L, 2L, 5L);
        }

        @Test
        @DisplayName("custom인 경우 COMMA, COLON 혼용되어선 안 된다.")
        void exception_regexValidator3() {
            boolean custom = RegexValidators.isCustom("//:\\n1:2,5");
            assertThat(custom).isEqualTo(false);
        }
    }

}
