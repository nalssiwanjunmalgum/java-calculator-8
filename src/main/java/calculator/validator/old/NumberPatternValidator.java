package calculator.validator.old;

import java.util.regex.Pattern;

public class NumberPatternValidator {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[1-9][0-9]*$");

    public boolean validate(String input) {
        return NUMBER_PATTERN.matcher(input).matches();
    }
}
