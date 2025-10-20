package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommonParser {
    private final String delimiter;

    public CommonParser(String delimiter) {
        this.delimiter = delimiter;
    }

    public List<Long> extractNumber(String input) {
        String[] split = input.split(Pattern.quote(delimiter));
        return Arrays.stream(split).mapToLong(Long::parseLong).boxed().toList();
    }
}
