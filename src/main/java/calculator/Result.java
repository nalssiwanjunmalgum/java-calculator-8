package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Result {
    private final String delimiter;
    private final String body;

    public Result(String delimiter, String body) {
        this.delimiter = delimiter;
        this.body = body;
    }

    public List<Long> extractNumber() {
        String[] split = body.split(Pattern.quote(delimiter));
        return Arrays.stream(split).mapToLong(Long::parseLong).boxed().toList();
    }
}
