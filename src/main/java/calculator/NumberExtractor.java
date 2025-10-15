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
        extractedNumbers.add(Integer.parseInt(trimmedInput));

        return extractedNumbers;
    }

    private boolean isBlank(String input) {
        return input.isBlank();
    }
}
