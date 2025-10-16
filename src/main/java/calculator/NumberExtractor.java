package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class NumberExtractor {
    private final List<Integer> extractedNumbers;

    public NumberExtractor() {
        this.extractedNumbers = new ArrayList<>();
    }

    public List<Integer> extractFrom(String input) {
        // 공백의 경우 0을 반환합니다(Edge Case)
        if (isBlank(input)) {
            extractedNumbers.add(0);
            return extractedNumbers;
        }
        // 공백을 제거하고 delimiter를 파악합니다
        String trimmedInput = input.replaceAll(" ", "");
        String finalInput = trimmedInput.replaceAll("[,:]", "*");

        String delimiter = findDelimiter(finalInput);

        if (delimiter == null) {
            throw new IllegalArgumentException("구분자가 존재하지 않습니다.");
        }

        // (문제) Custom의 경우 앞에 위치한 커스텀 구분자를 분석한 이후에 분석된 구분자로 수를 구분할 수 있어야 한다.
        // '\n' 까지 제외하고 구분해야 한다
        if (finalInput.startsWith("//")) {
            finalInput = finalInput.substring(4);
        }

        String[] split = finalInput.split(Pattern.quote(delimiter));
        List<Integer> finalNumbers = Arrays.stream(split)
                .map(Integer::parseInt)
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
}
