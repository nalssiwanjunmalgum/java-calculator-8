package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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

        // 기본 vs Custom
        if (isCommonPattern(trimmedInput)) {
            trimmedInput = trimmedInput.replaceAll("[,:]", "*");
        }

        // 구분자를 추출합니다
        String delimiter = findDelimiter(trimmedInput);

        if (delimiter == null) {
            throw new IllegalArgumentException("구분자가 존재하지 않습니다.");
        }

        // '\n' 까지 제외하고 구분해야 한다
        if (trimmedInput.startsWith("//")) {
            trimmedInput = trimmedInput.substring(5);
        }

        // 정제된 문자열, 구분자만 있으면 됨
        String[] split = trimmedInput.split(Pattern.quote(delimiter));
        List<Long> finalNumbers = Arrays.stream(split)
                .map(Long::parseLong)
                .toList();

        extractedNumbers.addAll(finalNumbers);
        return extractedNumbers;
    }

    private boolean isBlank(String input) {
        return input.isBlank();
    }

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

    private boolean isCommonPattern(String trimmedInput) {
        return new CommonPatternValidator().validate(trimmedInput);
    }
}
