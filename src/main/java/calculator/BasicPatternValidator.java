package calculator;

import java.util.regex.Pattern;

public class BasicPatternValidator {
    private static final Pattern COMMA_PATTERN = Pattern.compile("^[1-9][0-9]*(,[1-9][0-9]*)+$");
    private static final Pattern COLON_PATTERN = Pattern.compile("^[1-9][0-9]*(:[1-9][0-9]*)+$");

    public boolean validate(String input) {
        return COMMA_PATTERN.matcher(input).matches() || COLON_PATTERN.matcher(input).matches();
    }

    public boolean isCommaPattern(String trimmedInput) {
        return COMMA_PATTERN.matcher(trimmedInput).matches();
    }
}
