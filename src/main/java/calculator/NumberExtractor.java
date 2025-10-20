package calculator;

import calculator.parser.CommonParser;
import calculator.parser.CustomParser;
import calculator.util.Converter;
import calculator.validator.old.BasicPatternValidator;
import calculator.validator.old.CustomPatternValidator;
import calculator.validator.old.NumberPatternValidator;
import calculator.validator.refactorV1.RegexValidators;
import calculator.validator.refactorV1.Result;
import java.util.ArrayList;
import java.util.List;

public class NumberExtractor {
    private final List<Long> extractedNumbers;

    public NumberExtractor() {
        this.extractedNumbers = new ArrayList<>();
    }

    public List<Long> extractFrom(String input) {
        // 공백의 경우 0을 반환합니다(Edge Case)
        if (isBlank(input)) {
            extractedNumbers.add(0L);
            return extractedNumbers;
        }

        // 공백을 제거하고 delimiter를 파악합니다 (공통)
        String trimmedInput = Converter.eraseBlank(input);
        List<Long> numbers = null;

        if (RegexValidators.isNumber(trimmedInput)) {
            // 숫자인 경우
            CommonParser whitespaceParser = new CommonParser(" ");
            numbers = whitespaceParser.extractNumber(trimmedInput);
        }

        // Basic
        if (RegexValidators.isBasic(trimmedInput)) {
            trimmedInput = trimmedInput.replaceAll("[,:]", "*");
            CommonParser commonParser = new CommonParser("*");
            numbers = commonParser.extractNumber(trimmedInput);
        }

        // Custom인 경우
        if (RegexValidators.isCustom(trimmedInput)) {
            Result parsed = CustomParser.parse(trimmedInput);
            numbers = parsed.extractNumber();
        }

        if (numbers == null) {
            throw new IllegalArgumentException("구분자가 존재하지 않습니다.");
        }

        extractedNumbers.addAll(numbers);
        return extractedNumbers;
    }

    private boolean isBlank(String input) {
        return input.isBlank();
    }

    // 리팩토링 하는 과정에서 사용하지 않음
    protected String findDelimiter(String trimmedInput) {
        NumberPatternValidator numberPatternValidator = new NumberPatternValidator();
        BasicPatternValidator basicPatternValidator = new BasicPatternValidator();
        CustomPatternValidator customPatternValidator = new CustomPatternValidator();

        // 수로만 구성된 경우
        if (numberPatternValidator.validate(trimmedInput)) {
            return " ";
        }

        // 기본 구분자 파악
        if (basicPatternValidator.validate(trimmedInput)) {
            return "*";
        }

        // 커스텀 구분자 파악
        if (customPatternValidator.validate(trimmedInput)) {
            return customPatternValidator.extractDelimiter(trimmedInput);
        }

        return null;
    }
}
