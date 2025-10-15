package calculator;

import java.util.ArrayList;
import java.util.List;

public class NumberExtractor {
    private final List<Integer> extractedNumbers;

    public NumberExtractor() {
        this.extractedNumbers = new ArrayList<>();
    }

    public List<Integer> extractFrom(String input) {
        if (input.isBlank()) {
            extractedNumbers.add(0);
        }

        return extractedNumbers;
    }
}
