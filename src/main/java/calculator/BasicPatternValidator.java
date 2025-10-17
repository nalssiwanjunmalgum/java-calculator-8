package calculator;

import java.util.regex.Pattern;

public class BasicPatternValidator {
    private static final Pattern BASIC_PATTERN = Pattern.compile("^[1-9][0-9]*(\\*[1-9][0-9]*)*$");

    public boolean validate(String input) {
        return BASIC_PATTERN.matcher(input).matches();
    }
}
