package calculator.validator.old;

import java.util.regex.Pattern;

public class CommonPatternValidator {
    private static final Pattern COMMON_PATTERN = Pattern.compile("^[1-9][0-9]*([,:][1-9][0-9]*)*$");

    public boolean validate(String input) {
        return COMMON_PATTERN.matcher(input).matches();
    }
}
