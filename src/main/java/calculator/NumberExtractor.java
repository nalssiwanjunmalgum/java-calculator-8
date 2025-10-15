package calculator;

import java.util.ArrayList;
import java.util.List;

public class NumberExtractor {
    private final List<Integer> extractedNumbers;

    public NumberExtractor() {
        this.extractedNumbers = new ArrayList<>();
    }

    public List<Integer> extractFrom(String input) {
        if (isBlank(input)) {
            extractedNumbers.add(0);
            return extractedNumbers;
        }

        String trimmedInput = input.replaceAll(" ", "");
        String delimeter = findDelimeter(trimmedInput);

        return extractedNumbers;
    }

    private boolean isBlank(String input) {
        return input.isBlank();
    }

    private String findDelimeter(String trimmedInput) {
        // 수로만 구성된 경우

        // 기본 구분자 파악

        // 커스텀 구분자 파악
        return null;
    }
}
